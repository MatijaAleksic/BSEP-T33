package bsep.devices.dto;

public class DeviceMessageDTO {

    Long deviceid;

    String message;

    String name;

    public DeviceMessageDTO() { }

    public DeviceMessageDTO(Long deviceid, String message) {
        this.deviceid = deviceid;
        this.message = message;
    }

    public DeviceMessageDTO(long l, String name) {
        this.deviceid = l;
        this.name = name;
    }


    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
