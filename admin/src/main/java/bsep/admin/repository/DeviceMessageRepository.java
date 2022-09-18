package bsep.admin.repository;

import bsep.admin.model.CertificateRequest;
import bsep.admin.model.Device;
import bsep.admin.model.DeviceMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DeviceMessageRepository extends JpaRepository<DeviceMessage, Long> {

    ArrayList<DeviceMessage> findByDeviceid(Long deviceid);
}
