// import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute, Params, Router } from '@angular/router';
// import { Device } from 'src/app/models/device';
// import { DeviceService } from 'src/app/service/device.service';


// @Component({
//   selector: 'app-device-table',
//   templateUrl: './device-table.component.html',
//   styleUrls: ['./device-table.component.css']
// })
// export class DeviceTableComponent implements OnInit {

//   userId : number;

//   devices: Device[];
//   displayedColumns: string[] = ["id", "name", "activate", "deactivate"];


//   constructor(
//     private router: ActivatedRoute,
//     private deviceService: DeviceService

//   ) { }

//   ngOnInit() {
//     this.router.params.subscribe((params: Params) => this.userId = params['id']);

//     this.deviceService.getAll(this.userId).subscribe(
//       res => {
//         this.devices = res;
//       },
//       () => {
//         console.log("No devices to show.")
//       }
//     );

//   }

//   // configureButtons(){
//   //   alert("Uslo!")

//   //   this.devices.forEach(item => {
//   //     if(this.userDevices.includes(item)){
//   //       item.activate = true;
//   //       item.deactivate = false;
//   //     }else{
//   //       console.log(item);}
//   //     });
//   // }

//   activate(device){

//     this.deviceService.addDeviceForUser(this.userId, device.id).subscribe
//     (data => {
//       },
//       error => {
//       });

//     // this.devices = this.devices.filter(function( device ) {
//     //   return device.activate !== user.id;
//     // });

//     this.devices.forEach((dev) => {if(dev.id === device.id ){dev.activate = true}});

//   }

//   deactivate(device){
//     this.deviceService.removeDeviceForUser(this.userId, device.id).subscribe
//     (data => {
//       },
//       error => {
//       });

//     this.devices.forEach((dev) => {if(dev.id === device.id ){dev.activate = false}});
//   }

// }
