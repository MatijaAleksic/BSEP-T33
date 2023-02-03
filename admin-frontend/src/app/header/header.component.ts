import { Component, OnInit } from '@angular/core';
import {UserService} from '../service/user.service';
import {AuthService} from '../service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private signedIn = true;
  private userAdmin = true;

  constructor( 
    private authService: AuthService,
    private userService: UserService
    ) { }

  ngOnInit() {
  }

  hasSignedIn() {
    return !!this.authService.getCurrentUser();
  }

  logout() {
    this.authService.logout();
  }

  isUserAdmin(){
    if(this.authService.getCurrentUser() != null){

      for (let entry of this.authService.getUserRole()) {
        if(entry.name === "ROLE_ADMIN"){
          return true;
        }
      }
    }
  }

  userName() {
    const user = this.authService.getCurrentUser();
    return user.firstName + ' ' + user.lastName;
  }

}
