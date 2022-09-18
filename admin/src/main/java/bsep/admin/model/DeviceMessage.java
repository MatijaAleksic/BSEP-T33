package bsep.admin.model;

import javax.persistence.*;

@Entity
@Table(name = "device_messages")
public class DeviceMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;
    @Column(name = "deviceid", unique = false, nullable = false)
    private Long deviceid;
    @Column(name = "message", unique = false, nullable = false)
    private String message;

    public DeviceMessage() {
    }

    public DeviceMessage(Long deviceid, String message) {
        this.deviceid = deviceid;
        this.message = message;
    }

    public DeviceMessage(Long id, String name, Long deviceid, String message) {
        this.id = id;
        this.name = name;
        this.deviceid = deviceid;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
