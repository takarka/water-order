import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { User } from '../model/user.model';

@Injectable({ providedIn: 'root' })
export class AccountService {
    constructor(private http: HttpClient) {}

    get(): Observable<HttpResponse<User>> {
        return this.http.get<User>(SERVER_API_URL + 'uaa/api/account', {
            observe: 'response'
        });
    }

    save(account: any): Observable<HttpResponse<any>> {
        return this.http.post(SERVER_API_URL + 'uaa/api/account', account, {
            observe: 'response'
        });
    }
}
