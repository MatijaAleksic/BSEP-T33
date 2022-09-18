import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddUserComponent } from './forms/add-user/add-user.component';
import { LoginComponent } from './forms/login/login.component';
import { NewHomeComponent } from './forms/new-home/new-home.component';
import { RequestCertificateComponent } from './forms/request-certificate/request-certificate.component';
import { RevokeCertificateComponent } from './forms/revoke-certificate/revoke-certificate.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './service/auth.guard';
import { RoleGuard } from './service/role.guard';
import { AdminHomesTableComponent } from './tables/admin-homes-table/admin-homes-table.component';
import { AttendantTableComponent } from './tables/attendant-table/attendant-table.component';
import { CertificateRequestTableComponent } from './tables/certificate-request-table/certificate-request-table.component';
import { CertificateTableComponent } from './tables/certificate-table/certificate-table.component';
import { ConfigureHomeTablesComponent } from './tables/configure-home-tables/configure-home-tables.component';
import { DeviceMessagesComponent } from './tables/device-messages/device-messages.component';
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
    component: LoginComponent
  },
  {
    path: 'user-table',
    component: UserTableComponent,
    canActivate:[AuthGuard, RoleGuard],
  },
  {
    path: 'add-user',
    component: AddUserComponent,
    canActivate:[AuthGuard, RoleGuard]
  },
  // {
  //   path: 'device-table',
  //   component: DeviceTableComponent,
  // },
  {
    path: 'certificate-table',
    component: CertificateTableComponent,
    canActivate:[AuthGuard, RoleGuard]
  },
  {
    path: 'certificate-request-table',
    component: CertificateRequestTableComponent,
    canActivate:[AuthGuard, RoleGuard]
  },
  {
    path: 'request-certificate',
    component: RequestCertificateComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'revoke-certificate',
    component: RevokeCertificateComponent,
    canActivate:[AuthGuard, RoleGuard]
  },
  {
    path: 'admin-homes-table',
    component: AdminHomesTableComponent,
    canActivate:[AuthGuard, RoleGuard]
  },
  {
    path: 'configure-home-tables',
    component: ConfigureHomeTablesComponent,
    canActivate:[AuthGuard, RoleGuard]
  },
  {
    path: 'add-attendant',
    component: AttendantTableComponent,
    canActivate:[AuthGuard, RoleGuard]
  },
  {
    path: 'new-home',
    component: NewHomeComponent,
    canActivate:[AuthGuard, RoleGuard]
  },
  {
    path: 'device-messages',
    component: DeviceMessagesComponent,
    canActivate:[AuthGuard, RoleGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard, RoleGuard]
})
export class AppRoutingModule { }
