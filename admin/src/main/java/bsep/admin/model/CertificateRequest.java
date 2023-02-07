package bsep.admin.model;

import javax.persistence.*;

@Entity
@Table(name = "certificate_requests")
public class CertificateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "commonName", unique = true, nullable = false)
    private String commonName;

    @Column(name = "surname", unique = false, nullable = false)
    private String surname;

    @Column(name = "givenName", unique = false, nullable = false)
    private String givenName;

    @Column(name = "organization", unique = false, nullable = false)
    private String organization;

    @Column(name = "organizationUnit", unique = false, nullable = false)
    private String organizationUnit;

    @Column(name = "country", unique = false, nullable = false)
    private String country;

    @Column(name = "email", unique = false, nullable = false) //Promjeni unique u true posle developmenta
    private String email;

    @Column(name = "extension", unique = false, nullable = false)
    private String extension;


    public CertificateRequest(String commonName, String surname, String givenName, String organization, String organizationUnit, String country, String email, String extension) {
    	this.commonName = commonName;
        this.surname = surname;
        this.givenName = givenName;
        this.organization = organization;
        this.organizationUnit = organizationUnit;
        this.country = country;
        this.email = email;
        this.extension = extension;
    }

    public CertificateRequest() {
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
