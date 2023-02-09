package backend.admin.service;

import backend.admin.DTO.EmailDTO;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    public void sendSimpleMail(EmailDTO details){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);

            System.out.println("Proslo kroz mail!");
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMailWithAttachment(EmailDTO details){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            //.crt .cer .pem .der .pfx
            String fileName = "certificate." + details.getExtension();

            switch(details.getExtension()){
                case "cer": {
                    //.cer format
                    FileOutputStream fos = new FileOutputStream(fileName);
                    String pemEncodedCert = "-----BEGIN CERTIFICATE-----\n" +
                            Base64.getEncoder().encodeToString(details.getAttachment().getEncoded()) +
                            "\n-----END CERTIFICATE-----";
                    fos.write(pemEncodedCert.getBytes());
                    fos.close();
                    break;
                }
                case "pem": {
                    //.pem format

                    PemObject pemObject = new PemObject("CERTIFICATE", details.getAttachment().getEncoded());
                    try (PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
                        pemWriter.writeObject(pemObject);
                    }
                    break;
                }
                case "der": {
                    try (FileOutputStream fos = new FileOutputStream(fileName)) {
                        fos.write(details.getAttachment().getEncoded());
                    }
                }

                default:
                {
                    //.cer format
                    FileOutputStream fos = new FileOutputStream(fileName);
                    fos.write(details.getAttachment().getEncoded());
                    fos.close();
                }
            }

            FileSystemResource file = new FileSystemResource(new File(fileName));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            javaMailSender.send(mimeMessage);

            File deleteTempCertificate = new File(fileName);
            deleteTempCertificate.delete();

        }

        catch (MessagingException | CertificateEncodingException | IOException e) {
            e.printStackTrace();
        }


    }

}
