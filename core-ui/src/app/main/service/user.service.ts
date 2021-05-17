import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User, UserSearch } from '../shared/model/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
  private baseUrl = 'api/user';

  constructor(private http: HttpClient) {}

  getById(id: string): Observable<HttpResponse<User>> {
    return this.http.get<User>(`${this.baseUrl}/${id}`, {
      observe: 'response',
    });
  }

  search(searchDTO: UserSearch): Observable<HttpResponse<User[]>> {
    return this.http.post<User[]>(`${this.baseUrl}/search`, searchDTO, { observe: 'response' });
  }

  create(user: User): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.baseUrl, user, { observe: 'response' });
  }

  update(user: User): Observable<HttpResponse<User>> {
    return this.http.put<User>(this.baseUrl, user, { observe: 'response' });
  }

  delete(username: string): Observable<HttpResponse<any>> {
    return this.http.delete(`${this.baseUrl}/${username}`, { observe: 'response' });
  }

  me(): Observable<HttpResponse<User>> {
    return this.http.get<User>('http://localhost:8888/user/me', { observe: 'response' });
  }

  // removeImage(username: string): Observable<any> {
  //     return this.http.delete(`${this.baseUrl}/${username}/remove/image`);
  // }

  // getRoles(): Observable<HttpResponse<Role[]>> {
  //     return this.http.get<string[]>( `${this.baseUrl}/roles`, { observe: 'response' });
  // }

  // updateImage(username: string, file: File): Observable<HttpEvent<{}>> {
  //     const formdata: FormData = new FormData();
  //     formdata.append('file', file);

  //     const req = new HttpRequest('POST', SERVER_API_URL + 'uaa/api/users/image/' + username, formdata, {
  //         reportProgress: true,
  //         responseType: 'text'
  //     });
  //     return this.http.request(req);
  // }

  // getImage(username: string): Observable<Image> {
  //     return this.http.get<Image>(SERVER_API_URL + 'uaa/api/users/image/' + username);
  // }
}
