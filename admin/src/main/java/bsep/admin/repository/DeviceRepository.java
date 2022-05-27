package bsep.admin.repository;

import bsep.admin.model.Admin;
import bsep.admin.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findByName(String name);
}
