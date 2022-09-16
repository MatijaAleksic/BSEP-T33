import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  getAll(userId : number) {
    return this.apiService.get(this.config.device_url + '/' + userId);
  }

  // getAllForUser(id : number){
  //   return this.apiService.get(this.config.device_user_url + '/' + id);
  // }

  addDeviceForUser(user_id : number, device_id : number){
    return this.apiService.post(this.config.device_activate_url, {user_id : user_id, device_id : device_id });
  }

  removeDeviceForUser(user_id : number, device_id : number){
    return this.apiService.post(this.config.device_deactivate_url, {user_id : user_id, device_id : device_id });
  }

}
