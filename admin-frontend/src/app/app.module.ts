import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { CardComponent } from './card/card.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';

import {AngularMaterialModule} from './angular-material/angular-material.module';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ApiService} from './service/api.service';
import {FooService} from './service/foo.service';
import {AuthService} from './service/auth.service';
import {UserService} from './service/user.service';
import {ConfigService} from './service/config.service';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { LoginComponent } from './forms/login/login.component';
import { UserTableComponent } from './tables/user-table/user-table.component';
import { AddUserComponent } from './forms/add-user/add-user.component';
// import { DeviceTableComponent } from './tables/device-table/device-table.component';
import { CertificateTableComponent } from './tables/certificate-table/certificate-table.component';
import { CertificateRequestTableComponent } from './tables/certificate-request-table/certificate-request-table.component';
import { RequestCertificateComponent } from './forms/request-certificate/request-certificate.component';
import { RevokeCertificateComponent } from './forms/revoke-certificate/revoke-certificate.component';
import { AdminHomesTableComponent } from './tables/admin-homes-table/admin-homes-table.component';
import { ConfigureHomeTablesComponent } from './tables/configure-home-tables/configure-home-tables.component';
import { AttendantTableComponent } from './tables/attendant-table/attendant-table.component';
import { NewHomeComponent } from './forms/new-home/new-home.component';
import { DeviceMessagesComponent } from './tables/device-messages/device-messages.component';
import { AuthGuard } from './service/auth.guard';
import { RoleGuard } from './service/role.guard';


@NgModule({
  declarations: [
    AppComponent,
    CardComponent,
    HomeComponent,
    HeaderComponent,
    LoginComponent,
    UserTableComponent,
    AddUserComponent,
    // DeviceTableComponent,
    CertificateTableComponent,
    CertificateRequestTableComponent,
    RequestCertificateComponent,
    RevokeCertificateComponent,
    AdminHomesTableComponent,
    ConfigureHomeTablesComponent,
    AttendantTableComponent,
    NewHomeComponent,
    DeviceMessagesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NoopAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [ 
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    FooService,
    AuthService,
    AuthGuard,
    RoleGuard,
    ApiService,
    UserService,
    ConfigService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
