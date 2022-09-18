import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Home } from '../models/home';
import { UserService } from '../service';
import { HomeService } from '../service/home.service';

@Component({
  selector: 'app-my-homes-table',
  templateUrl: './my-homes-table.component.html',
  styleUrls: ['./my-homes-table.component.css']
})
export class MyHomesTableComponent implements OnInit {

  homes: Home[];
  displayedColumns: string[] = ["id", "name", "configure"];

  constructor(
    private router: Router,
    private homeService: HomeService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.homeService.getAllForUser(this.userService.currentUser.id).subscribe(
      res => {
        this.homes = res;
      },
      () => {
        console.log("No homes to show.")
      }
    );
  }

  seeDevices(element){
    this.homeService.setSelectedHome(element);
    this.router.navigate(['device-table']);
  }

}
