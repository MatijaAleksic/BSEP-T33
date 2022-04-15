package bsep.admin.controller;
import java.util.List;
import javax.validation.Valid;
import bsep.admin.Exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public Admin findOne(@PathVariable("id") Long id) throws UserNotFoundException {
        return service.findOne(id);
    }

    @PostMapping
    public Admin create(@RequestBody @Valid Admin entity) throws Exception {

        //entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        //entity.setIsDeleted(false);
        //List<Authority> auth = new ArrayList<Authority>();
        //auth.add(authorityService.findByName("ROLE_ADMIN"));
        //entity.setAuthorities(auth);

        return service.save(entity);
    }

    @PostMapping("/update")
    public Admin update(@RequestBody Admin entity) throws Exception {
        return service.update(entity);
    }

    @PostMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
    }
}
