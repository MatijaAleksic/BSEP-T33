import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(
    private userService: UserService,
    private router: Router) { }

  canActivate(){

    // return true;
    if (this.userService.userRole == "ROLE_ADMIN") {
          return true;
      }

    alert("You dont have admin rights!")
    return false;
  }
  
}
