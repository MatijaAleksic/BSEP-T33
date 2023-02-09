package backend.admin.DTO;

import java.security.cert.Certificate;

public class EmailDTO {

    private String recipient;
    private String msgBody;
    private String subject;
    private Certificate attachment;
    private String extension;

    public EmailDTO() {
    }

    public EmailDTO(String recipient, String msgBody, String subject) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
    }

    public EmailDTO(String recipient, String msgBody, String subject, Certificate attachment) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
        this.attachment = attachment;
    }

    public EmailDTO(String recipient, String msgBody, String subject, Certificate attachment, String extension) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
        this.attachment = attachment;
        this.extension = extension;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Certificate getAttachment() {
        return attachment;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setAttachment(Certificate attachment) {
        this.attachment = attachment;
    }
}
