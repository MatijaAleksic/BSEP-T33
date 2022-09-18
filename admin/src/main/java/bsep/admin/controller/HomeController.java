package bsep.admin.controller;

import bsep.admin.DTO.*;
import bsep.admin.Exceptions.UserNotFoundException;
import bsep.admin.model.Device;
import bsep.admin.model.Home;
import bsep.admin.model.User;
import bsep.admin.service.DeviceService;
import bsep.admin.service.HomeService;
import bsep.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    @Autowired
    private final HomeService homeService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final DeviceService deviceService;

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);



    public HomeController(HomeService homeService, UserService userService, DeviceService deviceService) {
        this.homeService = homeService;
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('READ_HOME_FOR_USER')")
    public ResponseEntity<?> findAllForUser(@PathVariable Long userId) {

        Set<Home> user_homes = userService.findById(userId).getHomes();
        List<HomeDTO> returnHomes = new ArrayList<HomeDTO>();

        for(Home home : user_homes){
            returnHomes.add(new HomeDTO(home.getId(), home.getName()));
        }

        LOG.info("Find all homes for user");
        return new ResponseEntity<>(returnHomes, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_ALL_HOMES')")
    public ResponseEntity<?> findAll()  {
        List<Home> homes = this.homeService.findAll();

        if(homes.size() == 0){
            return new ResponseEntity<>("Ne postoji ni jedna kuca u bazi podataka", HttpStatus.NOT_FOUND);
        }
        ArrayList<HomeDTO> homeDTOS = new ArrayList<>();
        for(Home home : homes){
            homeDTOS.add(new HomeDTO(home.getId(), home.getName()));
        }
        LOG.info("Find all homes");

        return new ResponseEntity<>(homeDTOS, HttpStatus.OK);

    }

    @PostMapping("/get")
    @PreAuthorize("hasAuthority('READ_HOME')")
    public ResponseEntity<?> getOne(@RequestBody IdentificationDTO homeDTO) {

        Home existingHome = this.homeService.findById(homeDTO.getId());

        if(existingHome == null){
            return new ResponseEntity<>("Ne postoji kuca sa tim id-om", HttpStatus.BAD_REQUEST);
        }

        List<Device> allDevices = this.deviceService.findAll();
        Set<Device> userDevices = existingHome.getDevices();

        List<DeviceDTO> returnDevices = new ArrayList<DeviceDTO>();

        for(Device dev : allDevices){
            if(userDevices.contains(dev)){
                returnDevices.add(new DeviceDTO(dev.getId(), dev.getName(), true));
            }
            else{
                returnDevices.add(new DeviceDTO(dev.getId(), dev.getName(), false));
            }
        }

        ReturnHomeDTO ret = new ReturnHomeDTO();
        ret.setName(existingHome.getName());
        ret.setId(existingHome.getId());
        ret.setUsers(existingHome.getUsers());
        ret.setDevices(returnDevices);

        LOG.info("Get home with id");
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping("/getOne")
    @PreAuthorize("hasAuthority('READ_DEVICES_FOR_HOME')")
    public ResponseEntity<?> getDevicesForHome(@RequestBody IdentificationDTO homeDTO) {

        Home existingHome = this.homeService.findById(homeDTO.getId());

        if(existingHome == null){
            return new ResponseEntity<>("Ne postoji kuca sa tim id-om", HttpStatus.BAD_REQUEST);
        }

        Set<Device> userDevices = existingHome.getDevices();

        LOG.info("Get devices for home id");
        return new ResponseEntity<>(userDevices, HttpStatus.OK);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_HOME')")
    public ResponseEntity<?> create(@RequestBody HomeDTO homeDTO) {

        if(homeDTO.getName().equalsIgnoreCase("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Home newHome = new Home();
        newHome.setName(homeDTO.getName());

        this.homeService.save(newHome);

        LOG.info("Create home");
        return new ResponseEntity<>("Home successfully created!", HttpStatus.CREATED);
    }


    @PostMapping("/user/add")
    @PreAuthorize("hasAuthority('ADD_HOME_ATTENDANT')")
    public ResponseEntity<?> addUser(@RequestBody DoubleIdnetificationDTO entity) throws Exception {

        User existUser = this.userService.findById(entity.getSecond());
        if(existUser == null){
            throw new UserNotFoundException("User with given id doesnt exist");
        }
        Home existingHome = this.homeService.findById(entity.getFirst());
        if (existingHome == null) {
            throw new UserNotFoundException("Home with given id doesnt exist");
        }

        existingHome.getUsers().add(existUser);
        this.homeService.save(existingHome);

        LOG.info("Add home attendant");
        return new ResponseEntity<>("User succesfully added to Home!", HttpStatus.OK);
    }

    @PostMapping("/user/remove")
    @PreAuthorize("hasAuthority('REMOVE_HOME_ATTENDANT')")
    public ResponseEntity<?> removeUser(@RequestBody DoubleIdnetificationDTO entity) throws Exception {

        User existUser = this.userService.findById(entity.getSecond());
        if(existUser == null){
            return new ResponseEntity<>("User with given id doesnt exist!", HttpStatus.BAD_REQUEST);
        }
        Home existingHome = this.homeService.findById(entity.getFirst());
        if (existingHome == null) {
            return new ResponseEntity<>("Home with given id doesnt exist!", HttpStatus.BAD_REQUEST);
        }

        Set<User> homeUsers = existingHome.getUsers();
        if(!homeUsers.contains(existUser)){
            return new ResponseEntity<>("Home doesnt have user with given id!", HttpStatus.BAD_REQUEST);
        }

        homeUsers.remove(existUser);
        this.homeService.save(existingHome);

        LOG.info("Remove home attendant");
        return new ResponseEntity<>("User succesfully removed from Home!", HttpStatus.OK);
    }

    @PostMapping("/device/add")
    @PreAuthorize("hasAuthority('ADD_HOME_DEVICE')")
    public ResponseEntity<?> addDevice(@RequestBody DoubleIdnetificationDTO entity) throws Exception {

        Device existDevice = this.deviceService.findById(entity.getSecond());
        if(existDevice == null){
            throw new UserNotFoundException("Device with given id doesnt exist");
        }
        Home existingHome = this.homeService.findById(entity.getFirst());
        if (existingHome == null) {
            throw new UserNotFoundException("Home with given id doesnt exist");
        }

        existingHome.getDevices().add(existDevice);
        this.homeService.save(existingHome);

        LOG.info("Add device to home");
        return new ResponseEntity<>("Device succesfully added to Home!", HttpStatus.OK);
    }

    @PostMapping("/device/remove")
    @PreAuthorize("hasAuthority('REMOVE_HOME_DEVICE')")
    public ResponseEntity<?> removeDevice(@RequestBody DoubleIdnetificationDTO entity) throws Exception {

        Device existDevice = this.deviceService.findById(entity.getSecond());
        if(existDevice == null){
            return new ResponseEntity<>("Device with given id doesnt exist!", HttpStatus.BAD_REQUEST);
        }
        Home existingHome = this.homeService.findById(entity.getFirst());
        if (existingHome == null) {
            return new ResponseEntity<>("Home with given id doesnt exist!", HttpStatus.BAD_REQUEST);
        }

        Set<Device> homeUsers = existingHome.getDevices();
        if(!homeUsers.contains(existDevice)){
            return new ResponseEntity<>("Home doesnt have device with given id!", HttpStatus.BAD_REQUEST);
        }

        homeUsers.remove(existDevice);
        this.homeService.save(existingHome);

        LOG.info("Remove device to home");
        return new ResponseEntity<>("Device succesfully removed from Home!", HttpStatus.OK);
    }

    @PostMapping("/unattendants")
    @PreAuthorize("hasAuthority('READ_HOME_UNATTENDANTS')")
    public ResponseEntity<?> getAllUnatendants(@RequestBody IdentificationDTO entity) throws Exception {

        Home existingHome = this.homeService.findById(entity.getId());
        Set<User> houseUsers = existingHome.getUsers();

        List<User> allUsers = this.userService.findAll();

        ArrayList<User> retUsers = new ArrayList<>();
        for(User u : allUsers){
            if(!houseUsers.contains(u)) {
                retUsers.add(u);
            }
        }

        LOG.info("Read all home unattendants");
        return new ResponseEntity<>(retUsers, HttpStatus.OK);
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('CREATE_HOME')")
    public ResponseEntity<?> newHome(@RequestBody HomeDTO entity) throws Exception {
        Home newHome = new Home(entity.getName());
        List<Home> allHomes = this.homeService.findAll();
        for(Home home : allHomes){
            if(home.getName().equalsIgnoreCase(newHome.getName())){
                return new ResponseEntity<>("Home with given name alredy exists", HttpStatus.BAD_REQUEST);
            }
        }
        this.homeService.save(newHome);

        LOG.info("Create new home");
        return new ResponseEntity<>("Succefully created new home", HttpStatus.CREATED);
    }
}
