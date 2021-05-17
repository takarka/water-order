import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { API_BASE_URL } from '../constants/urls';
import { map } from 'rxjs/operators';
import { Address, City, Region } from './order.service';

@Injectable()
export class ProfileService {

  private url = API_BASE_URL + '/user/me';
  private addressUrl = API_BASE_URL + '/api/address';
  private regionUrl = API_BASE_URL + '/api/region';
  private cityUrl = API_BASE_URL + '/api/city';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  cities: City[];
  regions: Region[];
  constructor(private http: HttpClient) {}
  user: User;

  get() {
    return this.http
      .get<User>(`${this.url}/`, {
        headers: this.headers,
      })
      .pipe(map(user => (this.user = user)));
  }

  editProfile(profileData: EditProfileForm): any {
    this.user = { ...this.user, ...profileData };
    return this.http.post<any>(`${this.url}/`, profileData, {
      headers: this.headers,
    });
  }
  getAddress(id: string): any {
    return this.http.get<Address>(`${this.addressUrl}/` + id, {
      headers: this.headers,
    });
  }
  updateAddress(address: Address): any {
    return this.http.put<any>(`${this.addressUrl}/`, address, {
      headers: this.headers,
    });
  }
  saveAddress(address: Address): any {
    return this.http.post<any>(`${this.addressUrl}/`, address, {
      headers: this.headers,
    });
  }
  deleteAddress(id: string): any {
    return this.http.delete<Address>(`${this.addressUrl}/` + id, {
      headers: this.headers,
    });  }
  getCities(): any {
    return this.http.get<City[]>(`${this.cityUrl}/`, {
      headers: this.headers,
    });
  }
  getRegions(): any {
    return this.http.get<Region[]>(`${this.regionUrl}/`, {
      headers: this.headers,
    });
  }
  get profile() {
    return this.user;
  }

  get citiesList() {
    return this.cities;
  }

  get regionsList() {
    return this.regions;
  }
}
export class User {
  id: string;
  name: string;
  email: string;
  phone: string;
  imageUrl: string;
  emailVerified: boolean;
  provider: string;
  providerId: string;
}

export class EditProfileForm {
  constructor(public name?: string, public phone?: string) {
    this.name = name ? name : null;
    this.phone = phone ? phone : null;
  }
}
