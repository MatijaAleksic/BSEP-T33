import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeviceMessagesComponent } from './device-messages/device-messages.component';
import { DeviceTableComponent } from './device-table/device-table.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { DeviceMessage } from './models/deviceMessage';
import { MyHomesTableComponent } from './my-homes-table/my-homes-table.component';
import { AuthGuard } from './service/auth.guard';
import { RoleGuard } from './service/role.guard';


// todo: dodati Auth Guards na putanje
const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'my-homes-table',
    component: MyHomesTableComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'device-table',
    component: DeviceTableComponent,
    canActivate:[AuthGuard]
  },

  {
    path: 'device-messages',
    component: DeviceMessagesComponent,
    canActivate:[AuthGuard]
  },

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard, RoleGuard]
})
export class AppRoutingModule { }


