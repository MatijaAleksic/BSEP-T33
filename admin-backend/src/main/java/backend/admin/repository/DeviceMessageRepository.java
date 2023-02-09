package backend.admin.repository;

import backend.admin.model.DeviceMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DeviceMessageRepository extends JpaRepository<DeviceMessage, Long> {

    ArrayList<DeviceMessage> findByDeviceid(Long deviceid);
}
