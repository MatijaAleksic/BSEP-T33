export class User {

    public id : string;
    public firstName: string;
    public lastName: string;
    public username: string;
    public email: string;


    constructor(id : string, username: string, email: string, firstName: string, lastName: string) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}