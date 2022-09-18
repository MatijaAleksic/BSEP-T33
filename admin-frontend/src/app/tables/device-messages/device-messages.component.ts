import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { interval } from 'rxjs';
import { DeviceMessage } from 'src/app/models/deviceMessage';
import { DeviceMessageService } from 'src/app/service/device-message.service';
import { HomeService } from 'src/app/service/home.service';

@Component({
  selector: 'app-device-messages',
  templateUrl: './device-messages.component.html',
  styleUrls: ['./device-messages.component.css']
})
export class DeviceMessagesComponent implements OnInit {

  devices: DeviceMessage[];
  displayedColumns: string[] = ["id", "name", "message"];

  private timer : any;
  private interval : any;

  constructor(
    private router: Router,
    private homeService : HomeService,
    private deviceMessageService : DeviceMessageService
  ) { }

  ngOnInit() {
    let device : any = this.homeService.getSelectedDevice();
    
    this.deviceMessageService.getAll().subscribe(
      res => {
        this.devices = res;
      },
      () => {
        console.log("No messages to show.")
      }
    );


    this.interval = interval(7000).subscribe(() => {

      console.log("LUMPUJE")
      this.deviceMessageService.getAll().subscribe(
        res => {
          this.devices = res;
        },
        () => {
          console.log("No messages to show.")
        }
      );
    });
  }

}
