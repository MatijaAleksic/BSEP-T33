import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { interval } from 'rxjs';
import { DeviceMessage } from '../models/deviceMessage';
import { DeviceMessageService } from '../service/device-message.service';
import { DeviceService } from '../service/device.service';
import { HomeService } from '../service/home.service';

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
    
    this.deviceMessageService.getMessagesForDevice(device.id).subscribe(
      res => {
        this.devices = res.body;
      },
      () => {
        console.log("No messages to show.")
      }
    );


    this.interval = interval(7000).subscribe(() => {

      console.log("LOOP")
      this.deviceMessageService.getMessagesForDevice(device.id).subscribe(
        res => {
          this.devices = res.body;
        },
        () => {
          console.log("No messages to show.")
        }
      );
    });
  }

  ngOnDestroy(){
    this.interval.unsubscribe();
    clearInterval(this.interval);
  }

}
