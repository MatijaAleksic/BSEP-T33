package bsep.admin.controller;

import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.CRLReason;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bsep.admin.DTO.CertificateDTO;
import bsep.admin.DTO.CertificateInfoDTO;
import bsep.admin.DTO.RevokeCertificateDTO;
import bsep.admin.service.CertificateRequestService;
import bsep.admin.service.CertificateService;

//@RestController
@Controller
@RequestMapping(value = "/certificate", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {

    @Autowired
    CertificateService certificateService;

    @Autowired
    CertificateRequestService cerRequestInfoService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ_CERTIFICATES')")
    public ResponseEntity<?> findAll() throws CertificateException, CRLException, IOException {

        List<Certificate> certificateInfoDTO = certificateService.getAllCertificates();
        List<CertificateInfoDTO> certInfos = new ArrayList<>();

        for (Certificate a : certificateInfoDTO){
            boolean isRevoked = certificateService.isRevoked(a);
            CRLReason reason = certificateService.revocationReason(a);
            String reasonOfRevocation = "";
            if(reason != null){
                reasonOfRevocation = reason.toString();
            }
            boolean isCa = certificateService.isSelfSigned((X509Certificate) a);

            X509Certificate b = (X509Certificate) a;

            X500Name x500name = new JcaX509CertificateHolder(b).getSubject();
            RDN cn = x500name.getRDNs(BCStyle.CN)[0];
            String commonName = IETFUtils.valueToString(cn.getFirst().getValue());
            cn = x500name.getRDNs(BCStyle.GIVENNAME)[0];
            String fullName = IETFUtils.valueToString(cn.getFirst().getValue());
            cn = x500name.getRDNs(BCStyle.E)[0];
            String email = IETFUtils.valueToString(cn.getFirst().getValue());

            CertificateInfoDTO newEntry = new CertificateInfoDTO(
                    commonName,
                    fullName,
                    email,
                    isRevoked,
                    reasonOfRevocation,
                    isCa
                    );
            certInfos.add(newEntry);
        }

        return new ResponseEntity<>(certInfos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CREATE_CERTIFICATE')")
    public ResponseEntity<?> create(@RequestBody CertificateDTO certificateDTO) {
        try {
            certificateService.generateCertificate(certificateDTO);

            //certificateService.createCertificate(certificateDTO);
            cerRequestInfoService.delete(certificateDTO.getId());
            return new ResponseEntity<>("Success", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('REVOKE_CERTIFICATE')")
    public ResponseEntity<?> revoke(@RequestBody RevokeCertificateDTO revokeCertificateDTO) {
        try {
            certificateService.revokeCertificate(revokeCertificateDTO);
        } catch (IOException | CRLException | CertificateException | OperatorCreationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
