import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IUser } from '../model/user.model';
import { createRequestOption } from '../util/request-util';
import { ITenant } from '../model/tenant.model';
import { Image } from '../model/image.model';

@Injectable({ providedIn: 'root' })
export class UserService {
    private resourceUrl = SERVER_API_URL + 'uaa/api/users';

    constructor(private http: HttpClient) {}

    create(user: IUser): Observable<HttpResponse<IUser>> {
        return this.http.post<IUser>(this.resourceUrl, user, { observe: 'response' });
    }

    update(user: IUser): Observable<HttpResponse<IUser>> {
        return this.http.put<IUser>(this.resourceUrl, user, { observe: 'response' });
    }

    find(username: string): Observable<HttpResponse<IUser>> {
        return this.http.get<IUser>(`${this.resourceUrl}/${username}`, { observe: 'response' });
    }

    query(tenant: string, req?: any): Observable<HttpResponse<IUser[]>> {
        const options = createRequestOption(req);
        return this.http.get<IUser[]>(`${this.resourceUrl}/list/${tenant}`, { params: options, observe: 'response' });
    }

    delete(username: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${username}`, { observe: 'response' });
    }

    removeImage(username: string): Observable<any> {
        return this.http.delete(`${this.resourceUrl}/${username}/remove/image`);
    }

    authorities(): Observable<string[]> {
        return this.http.get<string[]>(SERVER_API_URL + 'uaa/api/users/authorities');
    }

    tenants(): Observable<HttpResponse<ITenant[]>> {
        return this.http.get<ITenant[]>(SERVER_API_URL + 'uaa/api/users/tenants', { observe: 'response' });
    }

    updateImage(username: string, file: File): Observable<HttpEvent<{}>> {
        const formdata: FormData = new FormData();
        formdata.append('file', file);

        const req = new HttpRequest('POST', SERVER_API_URL + 'uaa/api/users/image/' + username, formdata, {
            reportProgress: true,
            responseType: 'text'
        });
        return this.http.request(req);
    }

    getImage(username: string): Observable<Image> {
        return this.http.get<Image>(SERVER_API_URL + 'uaa/api/users/image/' + username);
    }
}
