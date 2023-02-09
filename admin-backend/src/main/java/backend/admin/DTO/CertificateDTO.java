package backend.admin.DTO;

public class CertificateDTO {

    String email;
    String commonName;
    String extension;

    public CertificateDTO(String email, String commonName) {
        this.email = email;
        this.commonName = commonName;
    }

    public CertificateDTO(String email, String commonName, String extension) {
        this.email = email;
        this.commonName = commonName;
        this.extension = extension;
    }

    public CertificateDTO() {

    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
