export class CertificateRequest {

    public commonName : string;
    public surname: string;
    public givenName: string;
    public organization: string;
    public organizationUnit: string;
    public country : string;
    public email : string;
    public uid : number;


    constructor(commonName : string, surname: string, givenName: string, organization: string, organizationUnit: string, country : string, email : string, uid : number) {
        this.commonName = commonName;
        this.surname = surname;
        this.givenName = givenName;
        this.organization = organization;
        this.organizationUnit = organizationUnit;
        this.email = email;
        this.uid = uid;

    }
}