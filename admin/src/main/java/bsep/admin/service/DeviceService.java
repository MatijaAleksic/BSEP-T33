package bsep.admin.service;

import bsep.admin.model.Device;
import bsep.admin.model.User;
import bsep.admin.repository.DeviceRepository;
import bsep.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device findByName(String name) throws UsernameNotFoundException {
        return deviceRepository.findByName(name);
    }

    public Device findById(Long id) throws AccessDeniedException {
        return deviceRepository.findById(id).orElseGet(null);
    }

    public List<Device> findAll() throws AccessDeniedException {
        return deviceRepository.findAll();
    }

    public Device save(Device device) {
        return this.deviceRepository.save(device);
    }

    public void delete(Device device) {
        this.deviceRepository.delete(device);
    }
}
