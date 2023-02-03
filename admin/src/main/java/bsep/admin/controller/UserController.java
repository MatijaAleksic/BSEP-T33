package bsep.admin.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

import bsep.admin.Exceptions.ResourceConflictException;
import bsep.admin.Exceptions.UserAlredyExistsException;
import bsep.admin.Exceptions.UserNotFoundException;
import bsep.admin.model.Role;
import bsep.admin.model.User;
import bsep.admin.service.RoleService;
import bsep.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima READ_USER permisiju
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('READ_USER')")
	public User loadById(@PathVariable Long userId) {
		LOG.info("Get user by id");
		return this.userService.findById(userId);
	}

	@GetMapping("/user/all")
	//@PreAuthorize("hasAuthority('READ_USERS')")
	public List<User> loadAll() {
		LOG.info("Get all users");
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	@PreAuthorize("hasAuthority('FIND_USER')")
	public User user(Principal user) {
		LOG.info("Get user info");
		return this.userService.findByUsername(user.getName());
	}

	@PostMapping("/admin")
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	public ResponseEntity<?> createAdmin(@RequestBody @Valid User entity) throws Exception {

		User existUser = this.userService.findByUsername(entity.getUsername());

		if (existUser != null) {
			throw new UserAlredyExistsException("Admin with given username already exists");
		}

		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		entity.setEnabled(true);
		entity.setLastPasswordResetDate(new Timestamp(new Date().getTime()));

		entity.setRoles(roleService.findByName("ROLE_ADMIN"));

		LOG.info("Create admin");
		return new ResponseEntity<>(this.userService.save(entity), HttpStatus.CREATED);
	}
	@PostMapping("/user")
	@PreAuthorize("hasAuthority('CREATE_USER')")
	public ResponseEntity<?> createUser(@RequestBody @Valid User entity) throws Exception {

		User existUser = this.userService.findByUsername(entity.getUsername());

		if (existUser != null) {
			throw new UserAlredyExistsException("User with given username already exists");
		}

		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		entity.setEnabled(true);
		entity.setLastPasswordResetDate(new Timestamp(new Date().getTime()));

		entity.setRoles(roleService.findByName("ROLE_USER"));

		LOG.info("Create user");
		return new ResponseEntity<>(this.userService.save(entity), HttpStatus.CREATED);
	}


	@PostMapping("/user/delete")
	@PreAuthorize("hasAuthority('DELETE_USER')")
	public ResponseEntity<?> deleteUser(@RequestBody @Valid User entity) throws Exception {

		User existUser = this.userService.findByUsername(entity.getUsername());

		if (existUser == null) {
			throw new UserNotFoundException("User with given username doesnt exist");
		}

		this.userService.delete(existUser);

		LOG.info("Delete user");
		return new ResponseEntity<>("User succesfully deleted!", HttpStatus.OK);
	}
}
