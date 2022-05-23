package bsep.admin.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

import bsep.admin.model.User;
import bsep.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima READ_USER permisiju
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('READ_USER')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/user/all")
	@PreAuthorize("hasAuthority('READ_USERS')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	@PreAuthorize("hasAuthority('FIND_USER')")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}

//	@PostMapping
//	@PreAuthorize("hasAuthority('CREATE_USER')")
//	public User create(@RequestBody @Valid User entity) throws Exception {
//
//		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
//		entity.setEnabled(true);
//		entity.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
//
//		List<Authority> auth = new ArrayList<Authority>();
//
//		auth.add(authorityService.findByName("ROLE_ADMIN"));
//
//		entity.setAuthorities(auth);
//
//		return service.save(entity);
//	}

	@GetMapping("/foo")
    public Map<String, String> getFoo() {
        Map<String, String> fooObj = new HashMap<>();
        fooObj.put("foo", "bar");
        return fooObj;
    }
}
