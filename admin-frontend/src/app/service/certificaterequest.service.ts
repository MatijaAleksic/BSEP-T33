import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class CertificaterequestService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  getAll() {
    return this.apiService.get(this.config.certificate_request_url);
  }

  create(certificateRequest){
    return this.apiService.post(this.config.certificate_request_url + "/send_request", JSON.stringify(certificateRequest));
  }

  delete(id){
    return this.apiService.delete(this.config.certificate_request_url + "/delete", id);
  }
}
