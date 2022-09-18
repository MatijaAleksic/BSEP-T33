package bsep.mojakuca.dto;

public class DeviceMessageDTO {

    Long id;

    String name;
    String message;

    public DeviceMessageDTO() { }

    public DeviceMessageDTO(Long id, String message, String name) {
        this.id = id;
        this.message = message;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
