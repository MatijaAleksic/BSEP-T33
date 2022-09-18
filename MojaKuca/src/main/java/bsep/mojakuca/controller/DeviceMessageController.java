package bsep.mojakuca.controller;

import bsep.mojakuca.dto.IdentificationDTO;
import bsep.mojakuca.model.DeviceMessage;
import bsep.mojakuca.service.DeviceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/device_messsage", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceMessageController {

    @Autowired
    private DeviceMessageService deviceMessageService;


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('READ_ALL_DEVICES_MESSAGES')")
    public List<DeviceMessage> loadAll() {
        return this.deviceMessageService.findAll();
    }

    @PostMapping("/device")
    @PreAuthorize("hasAuthority('READ_DEVICES_MESSAGES_FOR_DEVICE')")
    public ResponseEntity<?> getMessagesForDevice(@RequestBody @Valid IdentificationDTO entity) throws Exception {

        ArrayList<DeviceMessage> allDeviceMessages = this.deviceMessageService.findByDeviceid(entity.getId());
        return new ResponseEntity<>(allDeviceMessages, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> createDeviceMessage(@RequestBody DeviceMessage entity) throws Exception {

        DeviceMessage newDeviceMessage =  new DeviceMessage();

        newDeviceMessage.setDeviceid(entity.getDeviceid());
        newDeviceMessage.setMessage(entity.getMessage());
        this.deviceMessageService.save(newDeviceMessage);

        return new ResponseEntity<>("Successfuly created message", HttpStatus.OK);
    }


}
