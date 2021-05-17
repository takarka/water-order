import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITenant, TenantProperty } from '../model/tenant.model';
import { SERVER_API_URL } from 'app/app.constants';


@Injectable({ providedIn: 'root' })
export class TenantService {
    private resourceUrl = SERVER_API_URL + 'uaa/api/tenants';

    constructor(private http: HttpClient) {}

    create(tenant: ITenant): Observable<HttpResponse<ITenant>> {
        return this.http.post<ITenant>(this.resourceUrl, tenant, { observe: 'response' });
    }

    update(tenant: ITenant): Observable<HttpResponse<ITenant>> {
        console.log('Update tenant');
        return this.http.put<ITenant>(this.resourceUrl, tenant, { observe: 'response' });
    }

    find(id: string): Observable<HttpResponse<ITenant>> {
        return this.http.get<ITenant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(): Observable<ITenant[]> {
        return this.http.get<ITenant[]>(this.resourceUrl);
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    createProperty(id: string, property: TenantProperty): Observable<HttpResponse<ITenant>> {
        return this.http.post<ITenant>(`${this.resourceUrl}/${id}/property`, property, { observe: 'response' });
    }

    updateProperty(id: string, property: TenantProperty): Observable<HttpResponse<ITenant>> {
        return this.http.put<ITenant>(`${this.resourceUrl}/${id}/property`, property, { observe: 'response' });
    }

    queryProperties(id: string): Observable<TenantProperty[]> {
        return this.http.get<TenantProperty[]>(`${this.resourceUrl}/${id}/properties`);
    }

    deleteProperty(id: string, key: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${id}/property/${key}`, { observe: 'response' });
    }
}
