package bsep.mojakuca.controller;

import bsep.mojakuca.dto.AddUserDeviceDTO;
import bsep.mojakuca.dto.DeviceDTO;
import bsep.mojakuca.exception.UserNotFoundException;
import bsep.mojakuca.model.Device;
import bsep.mojakuca.model.User;
import bsep.mojakuca.service.DeviceService;
import bsep.mojakuca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/device", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceController {

    @Autowired
    private final DeviceService deviceService;

    @Autowired
    private final UserService userService;

    public DeviceController(DeviceService deviceService, UserService userService) {
        this.deviceService = deviceService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('READ_DEVICES')")
    public List<DeviceDTO> findAllForUser(@PathVariable Long userId) {

        List<Device> devices = deviceService.findAll();
        Set<Device> user_devices = userService.findById(userId).getDevices();

        List<DeviceDTO> returnDevices = new ArrayList<DeviceDTO>();

        for(Device d : devices){
            if(user_devices.contains(d)){
                returnDevices.add(new DeviceDTO(d.getId(), d.getName(), true));
            }
            else{
                returnDevices.add(new DeviceDTO(d.getId(), d.getName(), false));
            }
        }
        return returnDevices;
    }

//    @GetMapping("user/{userId}")
//    @PreAuthorize("hasAuthority('READ_USER_DEVICES')")
//    public ResponseEntity<Set<Device>> loadByUserId(@PathVariable Long userId) throws UserNotFoundException {
//
//        User existingUser = this.userService.findById(userId);
//
//        if (existingUser == null) {
//            throw new UserNotFoundException("User with given id doesnt exists");
//        }
//
//        return new ResponseEntity<>(existingUser.getDevices(), HttpStatus.CREATED);
//
//    }

    @PostMapping("/activate")
    @PreAuthorize("hasAuthority('READ_USER_DEVICES')")
    public ResponseEntity<?> addDeviceForUser(@RequestBody AddUserDeviceDTO dto) throws UserNotFoundException {

        User existingUser = this.userService.findById(dto.getUser_id());

        if (existingUser == null) {
            throw new UserNotFoundException("User with given id doesnt exists");
        }

        Device device = this.deviceService.findById(dto.getDevice_id());
        if (device == null) {
            throw new UserNotFoundException("Device with given id doesnt exists");
        }
        Set<Device> devices = existingUser.getDevices();
        devices.add(device);
        existingUser.setDevices(devices);

        return new ResponseEntity<>(this.userService.save(existingUser), HttpStatus.CREATED);
    }

    @PostMapping("/deactivate")     
    @PreAuthorize("hasAuthority('READ_USER_DEVICES')")
    public ResponseEntity<?> removeDeviceForUser(@RequestBody AddUserDeviceDTO dto) throws UserNotFoundException {

        User existingUser = this.userService.findById(dto.getUser_id());

        if (existingUser == null) {
            throw new UserNotFoundException("User with given id doesnt exists");
        }

        Device device = this.deviceService.findById(dto.getDevice_id());
        if (device == null) {
            throw new UserNotFoundException("Device with given id doesnt exists");
        }
        Set<Device> devices = existingUser.getDevices();
        devices.remove(device);
        existingUser.setDevices(devices);

        return new ResponseEntity<>(this.userService.save(existingUser), HttpStatus.CREATED);
    }

}
