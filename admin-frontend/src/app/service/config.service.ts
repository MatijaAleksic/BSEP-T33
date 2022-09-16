import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private _api_url = 'https://localhost:8080';
  private _auth_url = this._api_url + '/auth';

  private _user_url = this._api_url + '/user';
  private _users_url = this._user_url + '/all';
  private _user_delete_url = this._user_url + '/delete';
  private _whoami_url = this._api_url + '/whoami';
  private _foo_url = this._api_url + '/foo';

  private _login_url = this._auth_url + '/login';
  private _signup_url = this._auth_url + '/signup';

  private _admin_url = this._api_url + '/admin';

  private _device_url = this._api_url + "/device";
  private _device_activate_url = this._device_url + "/activate";
  private _device_deactivate_url = this._device_url + "/deactivate";
  private _device_user_url = this._device_url + "/user";

  private _certificate_url = this._api_url + "/certificate";
  private _certificate_request_url = this._api_url + "/certificate_request";



  get login_url(): string {
    return this._login_url;
  }
  get whoami_url(): string {
    return this._whoami_url;
  }
  get user_url(): string {
    return this._user_url;
  }
  get users_url(): string {
    return this._users_url;
  }
  get foo_url(): string {
    return this._foo_url;
  }
  get signup_url(): string {
    return this._signup_url;
  }
  get admin_url(): string{
    return this._admin_url;
  }
  get user_delete_url(): string{
    return this._user_delete_url;
  }
  get device_url(): string{
    return this._device_url;
  }
  get device_user_url(): string{
    return this._device_user_url;
  }
  get device_activate_url(): string{
    return this._device_activate_url;
  }
  get device_deactivate_url(): string{
    return this._device_deactivate_url;
  }
  get certificate_url(): string{
    return this._certificate_url;
  }
  get certificate_request_url(): string{
    return this._certificate_request_url;
  }

}
