package bsep.mojakuca.model;

import bsep.mojakuca.model.Role;

import java.util.ArrayList;
import java.util.List;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenState {

    private String accessToken;
    private Long expiresIn;

    private List<Role> userRoles;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.userRoles = null;
    }

    public UserTokenState(String accessToken, long expiresIn,  List<Role> userRoles) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.userRoles = userRoles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }
}