import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  selectedHome : any;
  selectedDevice : any;

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }


  getAll() {
    return this.apiService.get(this.config.home_url);
  }

  getOne(){
    if(this.selectedHome != null){
      return this.apiService.post(this.config.home_url + "/get", this.selectedHome.id);
    }
  }

  getAllForUser(id){
    return this.apiService.get(this.config.home_url + "/" +id);
  }

  create(element){
    return this.apiService.post(this.config.home_url, element);
  }

  addAttendant(id){
    let send = {"first" : this.selectedHome.id , "second" : id }

    return this.apiService.post(this.config.home_url + "/user/add", send);
  }
  removeAttendant(element){
    return this.apiService.post(this.config.home_url + "/user/remove", element);
  }

  addDevice(element){
    return this.apiService.post(this.config.home_url + "/device/add", element);
  }
  removeDevice(element){
    return this.apiService.post(this.config.home_url + "/device/remove", element);
  }

  getUnattendants(){
    if(this.selectedHome != null){
      return this.apiService.post(this.config.home_url + "/unattendants", this.selectedHome.id);
    }
  }

  newHome(element){
    return this.apiService.post(this.config.home_url + "/new", element);
  }








  getSelectedHome(){
    return this.selectedHome;
  }
  setSelectedHome(element){
    this.selectedHome = element;
  }
  getSelectedDevice(){
    return this.selectedDevice;
  }
  setSelectedDevice(element){
    this.selectedDevice = element;
  }


}
