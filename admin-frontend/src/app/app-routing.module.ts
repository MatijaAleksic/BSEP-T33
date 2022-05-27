import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddUserComponent } from './add-user/add-user.component';
import { DeviceTableComponent } from './device-table/device-table.component';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
