package bsep.admin.DTO;

import bsep.admin.model.User;

import java.util.List;
import java.util.Set;

public class ReturnHomeDTO {

    private Long id;
    private String name;

    private Set<User> users;
    private List<DeviceDTO> devices;

    public ReturnHomeDTO() {
    }

    public ReturnHomeDTO(Long id, String name, Set<User> users, List<DeviceDTO> devices) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.devices = devices;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<DeviceDTO> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceDTO> devices) {
        this.devices = devices;
    }
}
