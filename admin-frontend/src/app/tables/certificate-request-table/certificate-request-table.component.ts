import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material';
import { Router } from '@angular/router';
import { CertificateRequest } from 'src/app/models/certificate-request';
import { CertificateService } from 'src/app/service/certificate.service';
import { CertificaterequestService } from 'src/app/service/certificaterequest.service';


@Component({
  selector: 'app-certificate-request-table',
  templateUrl: './certificate-request-table.component.html',
  styleUrls: ['./certificate-request-table.component.css']
})
export class CertificateRequestTableComponent implements OnInit {

  @ViewChild('myTable', {static: false}) myTable: MatTable<any>;
  
  certificateRequests: CertificateRequest[];
  displayedColumns: string[] = ["commonName", "surname", "givenName", "organization", "organizationUnit", "country", "email", "accept", "decline"];

  constructor(
    private router: Router,
    private certificateRequestService: CertificaterequestService,
    private certificateService: CertificateService
  ) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.certificateRequestService.getAll().subscribe(
      res => {
        this.certificateRequests = res;
      },
      () => {
        console.log("No certificate requests to show.")
      }
    );
  }

  accept(element){
    
    
    let certificate = { email : element.email, commonName: element.commonName, extension: element.certExtension}

    console.log(certificate);

    this.certificateService.create(certificate).subscribe(
      res => { console.log(res); },
      () => {
             this.certificateRequests.forEach((ele,index) => {if(ele.commonName === element.commonName ){ this.certificateRequests.splice(index, 1);}});
             this.myTable.renderRows();
            }
    );

  }

  decline(element){
    
    // let certificate = {id: element.uid}
    this.certificateRequestService.delete(element.commonName).subscribe(
      res => { 
        this.certificateRequests.forEach((ele,index) => {if(ele.commonName === element.commonName ){ this.certificateRequests.splice(index, 1);}});
            this.myTable.renderRows();
       },
      () => { }
    );
  }

}
