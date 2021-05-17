import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import * as jwt_decode from 'jwt-decode';
import { map } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { API_BASE_URL, GOOGLE_AUTH_URL } from '../constants/urls';
require('nativescript-localstorage');
import * as utilsModule from 'tns-core-modules/utils/utils';
import { Router } from '@angular/router';
import { AdvancedWebViewOptions, openAdvancedUrl } from 'nativescript-advanced-webview';
export const TOKEN_NAME = 'jwt_token';

@Injectable()
export class AuthService {
  private url = API_BASE_URL + '/auth';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private _authorizedSubject = new Subject<any>();
  constructor(private http: HttpClient, private router: Router) {}

  loginWithGoogle(returnUrl): any {
    // utilsModule.openUrl(GOOGLE_AUTH_URL + "?returnUrl=" + returnUrl);
    let opts: AdvancedWebViewOptions = {
      url: GOOGLE_AUTH_URL + '?returnUrl=' + returnUrl,
      toolbarColor: '#ff4081',
      toolbarControlsColor: '#333', // iOS only
      showTitle: false, // Android only
    };

    openAdvancedUrl(opts);
  }

  getToken(): string {
    return localStorage.getItem(TOKEN_NAME);
  }

  setToken(token: string): void {
    localStorage.setItem(TOKEN_NAME, token);
    if (!token) {
      this._authorizedSubject.next({ login: false });
    } else if (!this.isTokenExpired(token)) {
      this._authorizedSubject.next({ login: true });
    }
  }
  signOut(): any {
    localStorage.removeItem(TOKEN_NAME);
    this._authorizedSubject.next({ login: false });
    this.router.navigate(['login']);
  }

  getTokenExpirationDate(token: string): Date {
    if (!token) {
      return null;
    }
    const decoded = jwt_decode(token);

    const expirationDate = decoded.token_expiration_date;
    if (expirationDate === undefined) {
      return null;
    }

    const date = new Date(0);
    date.setUTCSeconds(expirationDate);
    return date;
  }

  get loginStatus() {
    return this._authorizedSubject;
  }

  isTokenExpired(token?: string): boolean {
    if (!token) {
      token = this.getToken();
    }
    if (!token) {
      return true;
    }

    const date = this.getTokenExpirationDate(token);
    if (!date || date === undefined) {
      return false;
    }
    return !(date.valueOf() > new Date().valueOf());
  }

  login(user: LoginInfo) {
    return this.http
      .post<AccessToken>(`${this.url}/login`, user, {
        headers: this.headers,
      })
      .pipe(
        map(val => {
          console.log(val);
          this.setToken(val.accessToken);
          console.log(this.getTokenExpirationDate(val.accessToken));
        })
      );
  }
  register(user: RegistrationForm) {
    return this.http.post<any>(`${this.url}/signup`, user, {
      headers: this.headers,
    });
  }
}
export interface AccessToken {
  accessToken: string;
  tokenType: string;
}
export class LoginInfo {
  constructor(public email?: string, public password?: string) {
    this.email = email ? email : null;
    this.password = password ? password : null;
  }
}
export class RegistrationForm {
  constructor(
    public name?: string,
    public phone?: string,
    public email?: string,
    public password?: string,
    public passwordVerify?: string
  ) {
    this.name = name ? name : null;
    this.phone = phone ? phone : null;
    this.email = email ? email : null;
    this.password = password ? password : null;
    this.passwordVerify = passwordVerify ? passwordVerify : null;
  }
}
