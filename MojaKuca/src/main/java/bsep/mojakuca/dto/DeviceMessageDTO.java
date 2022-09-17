package bsep.mojakuca.dto;

public class DeviceMessageDTO {

    Long id;
    String message;

    public DeviceMessageDTO() { }

    public DeviceMessageDTO(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
