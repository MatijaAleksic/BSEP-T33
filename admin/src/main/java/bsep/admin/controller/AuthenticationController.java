package bsep.admin.controller;

import javax.servlet.http.HttpServletRequest;
import bsep.admin.DTO.JwtAuthenticationRequest;
import bsep.admin.DTO.UserTokenState;
import bsep.admin.model.User;
import bsep.admin.service.UserService;
import bsep.admin.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest request) {


        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String fingerprint = tokenUtils.generateFingerprint();
        String jwt = tokenUtils.generateToken(user.getUsername(), fingerprint);
        int expiresIn = tokenUtils.getExpiredIn();

        // Kreiraj cookie
        // String cookie = "__Secure-Fgp=" + fingerprint + "; SameSite=Strict; HttpOnly; Path=/; Secure";  // kasnije mozete probati da postavite i ostale atribute, ali tek nakon sto podesite https
        String cookie = "Fingerprint=" + fingerprint + "; Path=/; HttpOnly; SameSite=None; Secure;"; //Path=/;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", cookie);

        // Vrati token kao odgovor na uspesnu autentifikaciju


        LOG.info("Received request from ip {}: {} {}", request.getRemoteAddr(), request.getMethod(), request.getRequestURI());

        return ResponseEntity.ok().headers(headers).body(new UserTokenState(jwt, expiresIn, user));
    }

    // Endpoint za registraciju novog korisnika
//    @PostMapping("/signup")
//    public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {
//
//        User existUser = this.userService.findByUsername(userRequest.getUsername());
//
//        if (existUser != null) {
//            throw new ResourceConflictException(userRequest.getId(), "Username already exists");
//        }
//
//        User user = this.userService.save(userRequest);
//
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
}