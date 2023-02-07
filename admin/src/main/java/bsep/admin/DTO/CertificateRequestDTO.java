package bsep.admin.DTO;

public class CertificateRequestDTO {
    private String commonName;
    private String surname;
    private String givenName;
    private String organization;
    private String organizationUnit;
    private String country;
    private String email;

    private String certExtension;

    public CertificateRequestDTO() {
    }

    public CertificateRequestDTO(String commonName, String surname, String givenName, String organization,
                                 String organizationUnit, String country, String email) {
    	this.commonName = commonName;
        this.surname = surname;
        this.givenName = givenName;
        this.organization = organization;
        this.organizationUnit = organizationUnit;
        this.country = country;
        this.email = email;
    }

    public CertificateRequestDTO(String commonName, String surname, String givenName, String organization, String organizationUnit, String country, String email, String certExtension) {
        this.commonName = commonName;
        this.surname = surname;
        this.givenName = givenName;
        this.organization = organization;
        this.organizationUnit = organizationUnit;
        this.country = country;
        this.email = email;
        this.certExtension = certExtension;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertExtension() {
        return certExtension;
    }

    public void setCertExtension(String certExtension) {
        this.certExtension = certExtension;
    }
}
