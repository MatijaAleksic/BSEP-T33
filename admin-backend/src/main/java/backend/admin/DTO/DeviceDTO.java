package backend.admin.DTO;

public class DeviceDTO {
    private Long id;
    private String name;
    private boolean activate;

    public DeviceDTO() {
    }

    public DeviceDTO(Long id, String name, boolean activate) {
        this.id = id;
        this.name = name;
        this.activate = activate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

}
