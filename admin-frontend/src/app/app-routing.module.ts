import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddUserComponent } from './add-user/add-user.component';
import { CertificateRequestTableComponent } from './certificate-request-table/certificate-request-table.component';
import { CertificateTableComponent } from './certificate-table/certificate-table.component';
import { DeviceTableComponent } from './device-table/device-table.component';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RequestCertificateComponent } from './request-certificate/request-certificate.component';
import { RevokeCertificateComponent } from './revoke-certificate/revoke-certificate.component';
import { UserTableComponent } from './user-table/user-table.component';


// todo: dodati Auth Guards na putanje
const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'user-table',
    component: UserTableComponent,
  },
  {
    path: 'add-user',
    component: AddUserComponent,
  },
  {
    path: 'device-table',
    component: DeviceTableComponent,
  },
  {
    path: 'certificate-table',
    component: CertificateTableComponent,
  },
  {
    path: 'certificate-request-table',
    component: CertificateRequestTableComponent,
  },
  {
    path: 'request-certificate',
    component: RequestCertificateComponent,
  },
  {
    path: 'revoke-certificate',
    component: RevokeCertificateComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
