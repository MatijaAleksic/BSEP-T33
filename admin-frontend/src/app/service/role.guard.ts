import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router) { }

  canActivate(){

    if(this.authService.getUserRole().some(obj => obj.name === "ROLE_ADMIN")){
      return true;
    }
    else{
      alert("You dont have admin rights!")
      return false;
    }
  }
  
}
