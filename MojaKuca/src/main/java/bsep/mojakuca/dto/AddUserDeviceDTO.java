package bsep.mojakuca.dto;

public class AddUserDeviceDTO {

    private Long user_id;
    private Long device_id;

    public AddUserDeviceDTO() {
    }

    public AddUserDeviceDTO(Long user_id, Long device_id) {
        this.user_id = user_id;
        this.device_id = device_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }
}
