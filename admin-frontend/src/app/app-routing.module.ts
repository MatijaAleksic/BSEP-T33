import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddUserComponent } from './forms/add-user/add-user.component';
import { LoginComponent } from './forms/login/login.component';
import { NewHomeComponent } from './forms/new-home/new-home.component';
import { RequestCertificateComponent } from './forms/request-certificate/request-certificate.component';
import { RevokeCertificateComponent } from './forms/revoke-certificate/revoke-certificate.component';
import { HomeComponent } from './home/home.component';
import { AdminHomesTableComponent } from './tables/admin-homes-table/admin-homes-table.component';
import { AttendantTableComponent } from './tables/attendant-table/attendant-table.component';
import { CertificateRequestTableComponent } from './tables/certificate-request-table/certificate-request-table.component';
import { CertificateTableComponent } from './tables/certificate-table/certificate-table.component';
import { ConfigureHomeTablesComponent } from './tables/configure-home-tables/configure-home-tables.component';
// import { DeviceTableComponent } from './tables/device-table/device-table.component';
import { UserTableComponent } from './tables/user-table/user-table.component';



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
  // {
  //   path: 'device-table',
  //   component: DeviceTableComponent,
  // },
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
  {
    path: 'admin-homes-table',
    component: AdminHomesTableComponent,
  },
  {
    path: 'configure-home-tables',
    component: ConfigureHomeTablesComponent,
  },
  {
    path: 'add-attendant',
    component: AttendantTableComponent,
  },
  {
    path: 'new-home',
    component: NewHomeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
