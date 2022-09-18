export class DeviceMessage {

    public id : number;
    public deviceid : number;
    public name: string;
    public message: string;

    constructor(id : number, deviceid: number, name: string, message: string) {
        this.id = id;
        this.deviceid = deviceid;
        this.name = name;
        this.message = message;
    }
}