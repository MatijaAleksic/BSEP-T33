import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Device } from '../models/device';
import { DeviceMessage } from '../models/deviceMessage';
import { Home } from '../models/home';
import { HomeService } from '../service/home.service';

@Component({
  selector: 'app-device-table',
  templateUrl: './device-table.component.html',
  styleUrls: ['./device-table.component.css']
})
export class DeviceTableComponent implements OnInit {


  devices: DeviceMessage[];
  home : Home = new Home(999, "NAN");

  displayedColumns: string[] = ["id", "name", "messages"];

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
    this.homeService.getForOne().subscribe(
      res => {
        this.devices = res.body;
      },
      () => {
        console.log("Error.")
      }
    );
  }

  seeMessages(element){
    this.homeService.setSelectedDevice(element);
    this.router.navigate(['device-messages']);
  }

}
