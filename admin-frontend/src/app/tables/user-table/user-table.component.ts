import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/service';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  users: User[];
  displayedColumns: string[] = ["id", "firstName", "lastName", "username", "email", "delete"];


  constructor(
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.userService.getAll().subscribe(
      res => {
        this.users = res;

      //   const datePipe = new DatePipe('en-US');
      //   this.users.forEach( (element) => {
      //     element.lastPasswordResetDate = datePipe.transform(element.lastPasswordResetDate, 'dd/MM/yyyy') || "";
      // });
      },
      () => {
        console.log("No users to show.")
      }
    );
  }

  addNew() {
    this.router.navigate([`add-user`]);
  }

  deleteAdmin(user : User){
    this.userService.delete(user).subscribe(
      res => {
        console.log(res);
      },
      error => {
      });

    this.users = this.users.filter(function( admin ) {
      return admin.id !== user.id;
    });
  }

}
