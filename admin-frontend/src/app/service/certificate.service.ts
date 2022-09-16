import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class CertificateService {

  revokeEmail : string;

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  getAll() {
    return this.apiService.get(this.config.certificate_url);
  }

  create(certificate){
    return this.apiService.post(this.config.certificate_url, JSON.stringify(certificate));
  }

  revoke(revocation){
    return this.apiService.put(this.config.certificate_url, JSON.stringify(revocation));
  }


  setRevokeEmail(revokeEmail){
    this.revokeEmail = revokeEmail;
  }
  getRevokeEmail(){
    return this.revokeEmail;
  }
}
