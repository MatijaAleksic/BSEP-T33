export class Certificate {

    public commonName : string;
    public fullName: string;
    public email: string;
    public revoked: boolean;
    public revocationReason: string;
    public isCA : boolean;
    public children : any;


    constructor(commonName : string, fullName: string, email: string, revoked: boolean, revocationReason: string, isCA : boolean, children : any) {
        this.commonName = commonName;
        this.fullName = fullName;
        this.email = email;
        this.revoked = revoked;
        this.revocationReason = revocationReason;
        this.isCA = isCA;
        this.children = children;

    }
}