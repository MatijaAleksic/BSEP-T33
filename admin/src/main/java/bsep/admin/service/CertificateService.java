package bsep.admin.service;

import bsep.admin.DTO.CertificateDTO;
import bsep.admin.DTO.RevokeCertificateDTO;
import bsep.admin.DTO.SubjectDataDTO;
import bsep.admin.keystore.KeyStoreReader;
import bsep.admin.keystore.KeyStoreWriter;
import bsep.admin.model.CertificateRequest;
import bsep.admin.model.IssuerData;
import bsep.admin.model.SubjectData;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v2CRLBuilder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

import bsep.admin.keystore.Initialize;
@Service
public class CertificateService {

    @Autowired
    CertificateRequestService certificateRequestService;

    @Autowired
    private KeyStoreWriter keyStoreWriter;

    @Autowired
    private KeyStoreReader keyStoreReader;

    @PostConstruct
    private void init() {
        Security.addProvider(new BouncyCastleProvider());
    }


    public SubjectDataDTO getSubjectData(CertificateDTO certificateDTO){
        CertificateRequest request =  certificateRequestService.findByEmail(certificateDTO.getEmail());

        KeyPair keyPairSubject = Initialize.generateKeyPair();

        // Datumi od kad do kad vazi sertifikat
        SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
//
//        LocalDateTime startDate = LocalDateTime.now();
//        LocalDateTime endDate = startDate.plusYears(2);

        Date startDate = new Date();
        //Izdaje se sertifikat na dve godine
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.YEAR, 2);
        Date endDate = c.getTime();

        // Serijski broj sertifikata
        List<Certificate> certs = getAllCertificates();
        String sn = Integer.toString((certs.size()+1));

        // klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, request.getCommonName());
        builder.addRDN(BCStyle.SURNAME, request.getSurname());
        builder.addRDN(BCStyle.GIVENNAME, request.getGivenName());
        builder.addRDN(BCStyle.O, request.getOrganization());
        builder.addRDN(BCStyle.OU, request.getOrganizationUnit());
        builder.addRDN(BCStyle.C, request.getCommonName());
        builder.addRDN(BCStyle.E, request.getEmail());
        builder.addRDN(BCStyle.UID, request.getUid().toString());

        // Kreiraju se podaci za sertifikat, sto ukljucuje:
        // - javni kljuc koji se vezuje za sertifikat
        // - podatke o vlasniku
        // - serijski broj sertifikata
        // - od kada do kada vazi sertifikat
        assert keyPairSubject != null;
        SubjectData sd = new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
        return new SubjectDataDTO(keyPairSubject.getPrivate(), sd);

    }

    public void generateCertificate(CertificateDTO dto) {
        try {

            SubjectDataDTO subjectDataDTO = getSubjectData(dto);

            IssuerData caIssuer = keyStoreReader.readIssuerFromStore("admin@gmail.com");
            SubjectData subjectData = subjectDataDTO.getSd();

            // Posto klasa za generisanje sertifiakta ne moze da primi direktno privatni kljuc pravi se builder za objekat
            // Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za potpisivanje sertifikata
            // Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje sertifiakta
            JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");

            // Takodje se navodi koji provider se koristi, u ovom slucaju Bouncy Castle
            builder = builder.setProvider("BC");

            // Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
            ContentSigner contentSigner = builder.build(caIssuer.getPrivateKey());

            // Postavljaju se podaci za generisanje sertifiakta
            X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(caIssuer.getX500name(),
                    new BigInteger(subjectData.getSerialNumber()),
                    subjectData.getStartDate(),
                    subjectData.getEndDate(),
                    subjectData.getX500name(),
                    subjectData.getPublicKey());
            // Generise se sertifikat
            X509CertificateHolder certHolder = certGen.build(contentSigner);

            // Builder generise sertifikat kao objekat klase X509CertificateHolder
            // Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
            certConverter = certConverter.setProvider("BC");

            X509Certificate newCert = certConverter.getCertificate(certHolder);

//            System.out.println("\n===== Podaci o izdavacu sertifikata =====");
//            System.out.println(newCert.getIssuerX500Principal().getName());
//            System.out.println("\n===== Podaci o vlasniku sertifikata =====");
//            System.out.println(newCert.getSubjectX500Principal().getName());
//            System.out.println("\n===== Sertifikat =====");
//            System.out.println("-------------------------------------------------------");
//            System.out.println(newCert);
//            System.out.println("-------------------------------------------------------");

            Certificate adminCert = keyStoreReader.readCertificate("admin@gmail.com");

            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            InputStream in = new ByteArrayInputStream(adminCert.getEncoded());
            X509Certificate adminNewCert = (X509Certificate)certFactory.generateCertificate(in);

            Certificate[] subjectCertificateChain = {newCert,adminNewCert};
            //Certificate[] subjectCertificateChain = keyStoreReader.readCertificateChain();

            //write(String alias, PrivateKey privateKey, Certificate[] certificateChain)
            keyStoreWriter.write(dto.getEmail(),subjectDataDTO.getPk(),subjectCertificateChain );
            keyStoreWriter.saveKeyStore();


//            // Konvertuje objekat u sertifikat
//            return certConverter.getCertificate(certHolder);

        } catch (IllegalArgumentException | IllegalStateException | OperatorCreationException | CertificateException e) {
            e.printStackTrace();
        }
    }


//    public void createCertificate(CertificateDTO certificateDTO) throws CertificateException, CRLException, IOException, CertificateNotValid, MultipleAliasFound {
//
//        Certificate[] issuerCertificateChain = keyStoreReader.readCertificateChain(certificateDTO.getEmail());
//        //get issuer
//
//        X509Certificate issuer = (X509Certificate) issuerCertificateChain[0];
//        if (!isCertificateValid(issuerCertificateChain))
//            throw new CertificateNotValid("Certificate with alias " + certificateDTO.getEmail() + " is not valid");
//
//        try {
//            if (issuer.getBasicConstraints() == -1 || !issuer.getKeyUsage()[5]) { //sertifikat nije ca
//                throw new CertificatNotCa("Certificate with alias " + certificateDTO.getEmail() + " is not CertificateAuthority(CA)");
//            }
//        } catch (NullPointerException | CertificatNotCa ignored) {
//        }
//
//        //String alias = cerRequestInfoService.findOne(certificateCreationDTO.getSubjectID()).getEmail();
//        String alias = getLastAlias(certificateDTO.getEmail());
//
//
//        Certificate certInfo = keyStoreReader.readCertificate(alias);
//        if (certInfo != null) {
//            if (!isRevoked(certInfo))
//                throw new MultipleAliasFound("Found multiple aliases with name: " + alias);
//        }
//    }

    public boolean isCertificateValid(Certificate[] chain) throws CertificateException, CRLException, IOException {

        if (chain == null) {
            return false;
        }

        X509Certificate cert;
        for (int i = 0; i < chain.length; i++) {
            cert = (X509Certificate) chain[i];

            if (isRevoked(cert)) {
                return false;
            }

            Date now = new Date();

            if (now.after(cert.getNotAfter()) || now.before(cert.getNotBefore())) {
                return false;
            }

            try {
                if (i == chain.length - 1) {
                    return isSelfSigned(cert);
                }
                X509Certificate issuer = (X509Certificate) chain[i + 1];
                cert.verify(issuer.getPublicKey());
                //OVDJE TREBA RETURN DA VRATI AKO GA JE POTPISAO JEDAN IZNAD
            } catch (SignatureException | InvalidKeyException e) {
                return false;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean isRevoked(Certificate cer) throws IOException, CertificateException, CRLException {

        CertificateFactory factory = CertificateFactory.getInstance("X.509");

        File file = new File("src/main/resources/adminCRLs.crl");

        byte[] bytes = Files.readAllBytes(file.toPath());
        X509CRL crl = (X509CRL) factory.generateCRL(new ByteArrayInputStream(bytes));

        return crl.isRevoked(cer);
    }

    public CRLReason revocationReason(Certificate cer) throws IOException, CertificateException, CRLException {

        CertificateFactory factory = CertificateFactory.getInstance("X.509");

        File file = new File("src/main/resources/adminCRLs.crl");

        byte[] bytes = Files.readAllBytes(file.toPath());
        X509CRL crl = (X509CRL) factory.generateCRL(new ByteArrayInputStream(bytes));

        if(crl.isRevoked(cer)) {
            return crl.getRevokedCertificate((X509Certificate) cer).getRevocationReason();
        }else{
            return null;
        }
    }

    public boolean isSelfSigned(X509Certificate cert) {
        try {
            cert.verify(cert.getPublicKey());
            return true;
        } catch (SignatureException | InvalidKeyException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getLastAlias(String email) {

        int lastAliasNumber = 0;
        Enumeration<String> aliases = keyStoreReader.getAllAliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (alias.contains(email) && alias.contains("|")) {
                int number = Integer.parseInt(alias.split("\\|")[1]);
                lastAliasNumber = Math.max(number, lastAliasNumber);
            }
        }
        if (lastAliasNumber != 0) {
            email += lastAliasNumber;
        }


        return email;
    }

    public List<Certificate> getAllCertificates() {

        List<Certificate> certificates = new ArrayList<>();

        Enumeration<String> aliases = keyStoreReader.getAllAliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            Certificate cer = keyStoreReader.readCertificate(alias);
            certificates.add(cer);
        }

        return certificates;
    }

    public void revokeCertificate(RevokeCertificateDTO revokeCertificateDTO) throws IOException, CRLException, OperatorCreationException, CertificateEncodingException {

        File file = new File("src/main/resources/adminCRLs.crl");

        byte[] bytes = Files.readAllBytes(file.toPath());

        X509CRLHolder holder = new X509CRLHolder(bytes);
        X509v2CRLBuilder crlBuilder = new X509v2CRLBuilder(holder);

        Certificate cer = keyStoreReader.readCertificate(revokeCertificateDTO.getSubjectAlias());
        JcaX509CertificateHolder certHolder = new JcaX509CertificateHolder((X509Certificate) cer);

        //11 UNSPECIFIED,  1 KEY_COMPROMISE,   2 CA_COMPROMISE
        int reason = 11;
        if(revokeCertificateDTO.getRevocationReason().equalsIgnoreCase("kljuc")){
            reason = 1;
        }else if(revokeCertificateDTO.getRevocationReason().equalsIgnoreCase("ca")){
            reason = 2;
        }

        crlBuilder.addCRLEntry(certHolder.getSerialNumber()/*The serial number of the revoked certificate*/, new Date() /*Revocation time*/, reason); /*Reason for cancellation*/;
        JcaContentSignerBuilder contentSignerBuilder = new JcaContentSignerBuilder("SHA256WithRSA");
        contentSignerBuilder.setProvider("BC");

        IssuerData issuer = keyStoreReader.readIssuerFromStore(revokeCertificateDTO.getSubjectAlias());

        X509CRLHolder crlHolder = crlBuilder.build(contentSignerBuilder.build(issuer.getPrivateKey()));
        JcaX509CRLConverter converter = new JcaX509CRLConverter();
        converter.setProvider("BC");

        X509CRL crl = converter.getCRL(crlHolder);

        bytes = crl.getEncoded();

        OutputStream os = new FileOutputStream("src/main/resources/adminCRLs.crl");
        os.write(bytes);
        os.close();
    }
}
