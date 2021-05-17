import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpResponse, HttpClient } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';
import { Service } from '../model/service.model';
import { Privilege } from '../model/privilege.model';
import { IUser } from '../model/user.model';

@Injectable({
    providedIn: 'root'
})
export class PrivilegeService {
    private resourceUrl = SERVER_API_URL + 'uaa/api/privileges';

    constructor(private http: HttpClient) {}

    query(serviceId: string): Observable<any[]> {
        return this.http.get<any[]>(SERVER_API_URL + 'uaa/api/privileges/service/' + serviceId); // `${this.resourceUrl}/${name}`
    }

    rolePrivileges(serviceId: string, roleId: string): Observable<any[]> {
        return this.http.get<any[]>(SERVER_API_URL + 'uaa/api/privileges/service/' + serviceId + '/' + roleId); // `${this.resourceUrl}/${name}`
    }

    find(id: string): Observable<any> {
        return this.http.get<any[]>(SERVER_API_URL + 'uaa/api/privileges/' + id);
    }

    services(): Observable<Service[]> {
        return this.http.get<Service[]>(SERVER_API_URL + 'api/gateway/services');
    }

    update(privilege: Privilege): Observable<HttpResponse<Privilege>> {
        return this.http.put<Privilege>(this.resourceUrl, privilege, { observe: 'response' });
    }

    create(privilege: Privilege): Observable<HttpResponse<Privilege>> {
        return this.http.post<Privilege>(this.resourceUrl, privilege, { observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    excludedUsers(id: string, tenantId: string, roleId: string): Observable<IUser[]> {
        return this.http.get<IUser[]>(`${this.resourceUrl}/${id}/${tenantId}/${roleId}/exluded-users`);
    }

    users(id: string, tenantId: string, roleId: string): Observable<IUser[]> {
        return this.http.get<IUser[]>(`${this.resourceUrl}/${id}/${tenantId}/${roleId}/users`);
    }

    addUsers(id: string, roleId: string, userIds: any[]): Observable<HttpResponse<any>> {
        return this.http.put(`${this.resourceUrl}/${id}/${roleId}/add-users`, userIds.join(), {
            observe: 'response'
        });
    }

    removeUsers(id: string, roleId: string, userIds: any[]): Observable<HttpResponse<any>> {
        return this.http.put(`${this.resourceUrl}/${id}/${roleId}/remove-users`, userIds.join(), {
            observe: 'response'
        });
    }

    permissions(tenantId: string, serviceId: string, roleId: string): Observable<any[]> {
        return this.http.get<any[]>(`${this.resourceUrl}/${tenantId}/${serviceId}/${roleId}/permissions`);
    }

    setPermissions(tenantId: string, serviceId: string, roleId: string, privilegeIds: any[]): Observable<HttpResponse<any>> {
        return this.http.put(`${this.resourceUrl}/${tenantId}/${serviceId}/${roleId}/permissions`, privilegeIds.join(), {
            observe: 'response'
        });
    }
}
