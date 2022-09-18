import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { HomeService } from 'src/app/service/home.service';

@Component({
  selector: 'app-attendant-table',
  templateUrl: './attendant-table.component.html',
  styleUrls: ['./attendant-table.component.css']
})
export class AttendantTableComponent implements OnInit {

  users: User[];
  displayedColumns: string[] = ["id", "firstName", "lastName", "username", "email", "addAttendant"];


  constructor(
    private router: Router,
    private homeService: HomeService
  ) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    if(this.homeService.getSelectedHome != null){
      this.homeService.getUnattendants().subscribe(
        res => {
          this.users = res.body;
        },
        () => {
          console.log("No unattendants to show.")
        }
      );
    }
  }


  Add(element){
    this.homeService.addAttendant(element.id).subscribe(
      res => {
      },
      error => {
        // console.log(error.message);
      });

    this.users = this.users.filter(function( admin ) {
      return admin.id !== element.id;
    });
  }

}
