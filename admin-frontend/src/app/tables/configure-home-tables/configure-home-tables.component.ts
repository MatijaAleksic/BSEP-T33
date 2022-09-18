import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material';
import { Router } from '@angular/router';
import { Device } from 'src/app/models/device';
import { Home } from 'src/app/models/home';
import { User } from 'src/app/models/user';
import { HomeService } from 'src/app/service/home.service';

@Component({
  selector: 'app-configure-home-tables',
  templateUrl: './configure-home-tables.component.html',
  styleUrls: ['./configure-home-tables.component.css']
})
export class ConfigureHomeTablesComponent implements OnInit {

  @ViewChild('myTable', {static: false}) myTable: MatTable<any>;

  users: User[];
  devices: Device[];
  home : Home = new Home(999, "NAN");

  displayedColumns: string[] = ["id", "firstName", "lastName", "username", "email", "remove"];
  displayedColumns1: string[] = ["id", "name", "activate", "deactivate"];

  constructor(
    private router: Router,
    // private userService: UserService
    private homeService : HomeService
  ) { }

  ngOnInit() {
    if(this.homeService.getSelectedHome() != null){
      this.getHouseInfo();
    }
  }

  getHouseInfo(){
    this.homeService.getOne().subscribe(
      res => {
        this.users = res.body.users;
        this.devices = res.body.devices;

        this.home.id = res.body.id;
        this.home.name = res.body.name;
      },
      () => {
        console.log("Error.")
      }
    );
  }

  removeAttendent(element){
    let send = {"first" : this.home.id , "second" : element.id }

    this.homeService.removeAttendant(send).subscribe
    (data => {
      console.log("Success!")
      },
      error => {
      });

      this.users.forEach((ele,index) => {if(ele.id === element.id ){ this.users.splice(index, 1);}});
      this.myTable.renderRows();
  }

  activate(element){

    let send = {"first" : this.home.id , "second" : element.id }

    this.homeService.addDevice(send).subscribe
    (data => {
      console.log("Success!")
      },
      error => {
      });

    this.devices.forEach((dev) => {if(dev.id === element.id ){dev.activate = true}});

  }

  deactivate(element){

    let send = {"first" : this.home.id , "second" : element.id }

    this.homeService.removeDevice(send).subscribe
    (data => {
      console.log("Success!")
      },
      error => {
      });

    this.devices.forEach((dev) => {if(dev.id === element.id ){dev.activate = false}});
  
  }

}
  