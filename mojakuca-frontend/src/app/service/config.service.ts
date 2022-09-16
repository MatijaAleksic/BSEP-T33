import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private _api_url = 'http://localhost:8081';
  private _auth_url = this._api_url + '/auth';


  private _whoami_url = this._api_url + '/whoami';
  private _foo_url = this._api_url + '/foo';

  private _login_url = this._auth_url + '/login';
  private _signup_url = this._auth_url + '/signup';

  private _device_url = this._api_url + "/device";
  private _device_activate_url = this._device_url + "/activate";
  private _device_deactivate_url = this._device_url + "/deactivate";
  private _device_user_url = this._device_url + "/user";



  get login_url(): string {
    return this._login_url;
  }
  get whoami_url(): string {
    return this._whoami_url;
  }
  get foo_url(): string {
    return this._foo_url;
  }
  get signup_url(): string {
    return this._signup_url;
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
}
