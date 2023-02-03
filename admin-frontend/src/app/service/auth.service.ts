import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { UserService } from './user.service';
import { ConfigService } from './config.service';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { _throw } from 'rxjs/observable/throw';

@Injectable()
export class AuthService {

  authStatus = false;

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private router: Router,
  ) {
  }

  private token = null;
  private access_token = null;
  private currentUser = null;
  private userRole : any = null;

  private date = null;


  login(user) {

    const body = {
      'username': user.username,
      'password': user.password
    };

    return this.apiService.post(this.config.login_url, JSON.stringify(body))
      .pipe(map((response) => {
        console.log('Login success');
        console.log(response);

        this.currentUser = response.user;
        this.userRole = response.user.roles;

        this.access_token = response.accessToken;
        this.authStatus = true;

        sessionStorage.setItem("jwt", response.accessToken);
      }));
  }


  logout() {
    this.currentUser = null;
    this.access_token = null;
    this.authStatus = false;
    this.router.navigate(['/login']);
  
  }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }

  getExpires(){
    return this.token;
  }

  getAuthStatus(){
    return this.authStatus;
  }

  getUserRole(){
    return this.userRole;
  }

  getCurrentUser(){
    return this.currentUser;
  }


  setCurrentUser(user){
    this.currentUser = user;
  }
  setUserRole(role){
    this.userRole = role;
  }

  setAuthStatus(authStatus){
    this.authStatus = authStatus;
  }

  setAccessToken(accessToken){
    this.access_token = accessToken;
  }


}
