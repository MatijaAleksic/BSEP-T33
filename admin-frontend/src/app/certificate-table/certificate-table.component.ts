import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Certificate } from '../models/certificate';
import { CertificateService } from '../service/certificate.service';

@Component({
  selector: 'app-certificate-table',
  templateUrl: './certificate-table.component.html',
  styleUrls: ['./certificate-table.component.css']
})
export class CertificateTableComponent implements OnInit {

  certificates: Certificate[];
  displayedColumns: string[] = ["commonName", "fullName", "email", "revoked", "revocationReason", "isCA", "revoke"];


  constructor(
    private router: Router,
    private certificateService: CertificateService
  ) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.certificateService.getAll().subscribe(
      res => {
        this.certificates = res;
      },
      () => {
        console.log("No certificates to show.")
      }
    );
  }

  revoke(element){
    this.certificateService.setRevokeEmail(element.email);
    this.router.navigate(['revoke-certificate']);
  }

}
