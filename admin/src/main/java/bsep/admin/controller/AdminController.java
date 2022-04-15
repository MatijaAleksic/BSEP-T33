package bsep.admin.controller;
import java.util.List;
import javax.validation.Valid;

import bsep.admin.Exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import bsep.admin.service.AdminService;
import bsep.admin.model.Admin;

@Transactional
@RestController
@RequestMapping("/admins")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final AdminService service;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    //@Autowired
    //private AuthorityService authorityService;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping
    public List<Admin> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, consumes = "text/plain")
    public ResponseEntity<Admin> findOne( @RequestBody String id) throws UserNotFoundException {
        Long realId;
        try{
            realId = Long.parseLong(id);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            return new ResponseEntity<>(service.findOne(realId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Admin> create(@RequestBody @Valid Admin entity) throws Exception {

        //entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        //entity.setIsDeleted(false);
        //List<Authority> auth = new ArrayList<Authority>();
        //auth.add(authorityService.findByName("ROLE_ADMIN"));
        //entity.setAuthorities(auth);
        try{
            return new ResponseEntity<>(service.save(entity), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Admin> update(@RequestBody Admin entity) throws Exception {
        try {
            return new ResponseEntity<>(service.update(entity), HttpStatus.OK);
        }catch(UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //@PostMapping("/delete/{id}")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = "text/plain")

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@RequestBody String id) throws Exception {
        Long realId;
        try{
            realId = Long.parseLong(id);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            service.delete(realId);
            return new ResponseEntity<>("Sucess!", HttpStatus.OK);
        }catch(UserNotFoundException e) {
            return new ResponseEntity<>("Fail!", HttpStatus.NOT_FOUND);
        }
    }
}
