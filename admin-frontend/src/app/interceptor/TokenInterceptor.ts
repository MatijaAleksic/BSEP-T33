import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor,
  HttpEvent
} from '@angular/common/http';
import { AuthService } from '../service/auth.service';

import { Observable, } from 'rxjs/Observable';

import { _throw } from 'rxjs/observable/throw';
import { UserService } from '../service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(
    public auth: AuthService,
    public userService: UserService
    ) { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.auth.tokenIsPresent()) {
      console.log(this.auth.getExpires().expiresIn)
      console.log(new Date().setMinutes(new Date().getMinutes() + 120))

      // if (new Date().getSeconds()this.auth.getExpires().expiresIn < Date.now() / 1000) {
      //   next(action);
      //   localStorage.clear();
      // }

      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.auth.getToken()}` 
        },
        withCredentials: true
      });
    }
  
    return next.handle(request);
  }
}
