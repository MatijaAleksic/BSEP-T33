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
    private userService: UserService,
    private config: ConfigService,
    private router: Router,
  ) {
  }

  private token = null;
  private access_token = null;
  private date = null;

  login(user) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    // const body = `username=${user.username}&password=${user.password}`;
    const body = {
      'username': user.username,
      'password': user.password
    };

    return this.apiService.post(this.config.login_url, JSON.stringify(body), loginHeaders)
      .pipe(map((response) => {
        console.log('Login success');

            // "ssl" : true,
            // "sslKey" : "src/assets/admin.key",
            // "sslCert" : "src/assets/admin@gmail.com.der",

        this.userService.userRole = response.body.userRoles[0];

        this.token = response.body;
        this.access_token = response.body.accessToken;
        this.date = new Date();
        sessionStorage.setItem("jwt", response.body.accessToken);
        this.authStatus = true;
      }));
  }


  logout() {
    this.userService.currentUser = null;
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


}
