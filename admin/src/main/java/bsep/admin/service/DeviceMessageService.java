package bsep.admin.service;

import bsep.admin.model.Device;
import bsep.admin.model.DeviceMessage;
import bsep.admin.repository.DeviceMessageRepository;
import bsep.admin.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceMessageService {

    @Autowired
    private DeviceMessageRepository deviceMessageRepository;


    public ArrayList<DeviceMessage> findByDeviceid(Long id) throws UsernameNotFoundException {
        return deviceMessageRepository.findByDeviceid(id);
    }

    public DeviceMessage findById(Long id) throws AccessDeniedException {
        return deviceMessageRepository.findById(id).orElseGet(null);
    }

    public List<DeviceMessage> findAll() throws AccessDeniedException {
        return deviceMessageRepository.findAll();
    }

    public DeviceMessage save(DeviceMessage device) {
        return this.deviceMessageRepository.save(device);
    }

    public void delete(DeviceMessage device) {
        this.deviceMessageRepository.delete(device);
    }
}
