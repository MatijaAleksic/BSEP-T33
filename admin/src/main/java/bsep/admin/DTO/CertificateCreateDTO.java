package bsep.admin.DTO;

import org.bouncycastle.asn1.x500.style.BCStyle;

public class CertificateCreateDTO {

    private String companyName;
    private String givenName;
    private String surName;
    private String organization;
    private String organizationUnit;
    private String country;
    private String email;
    private String userId;

    public CertificateCreateDTO() {
    }

    public CertificateCreateDTO(String companyName, String givenName, String surName, String organization, String organizationUnit, String country, String email, String userId) {
        this.companyName = companyName;
        this.givenName = givenName;
        this.surName = surName;
        this.organization = organization;
        this.organizationUnit = organizationUnit;
        this.country = country;
        this.email = email;
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
