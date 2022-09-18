package bsep.admin.service;

import bsep.admin.DTO.HomeDTO;
import bsep.admin.model.Device;
import bsep.admin.model.Home;
import bsep.admin.repository.DeviceRepository;
import bsep.admin.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    public Home findByName(String name) throws UsernameNotFoundException {
        return homeRepository.findByName(name);
    }

    public Home findById(Long id) throws AccessDeniedException {
        return homeRepository.findById(id).orElseGet(null);
    }

    public List<Home> findAll() throws AccessDeniedException {
        return homeRepository.findAll();
    }

    public Home save(Home home) {
        return this.homeRepository.save(home);
    }

    public void delete(Home device) {
        this.homeRepository.delete(device);
    }

}
