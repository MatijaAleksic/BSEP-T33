import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class DeviceMessageService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }


  getAll() {
    return this.apiService.get(this.config.device_message_url + "/all");
  }

  getMessagesForDevice(id){
    let send = {"id" : id}
    return this.apiService.post(this.config.device_message_url + "/device", send);
  }

}
