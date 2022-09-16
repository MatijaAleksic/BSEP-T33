export class Device {

    public id : string;
    public name: string;

    public activate: boolean;

    constructor(id : string, name: string) {
        this.id = id;
        this.name = name;
        this.activate = true;
    }
}