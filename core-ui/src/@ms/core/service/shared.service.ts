import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { TimeZone } from '../model/timezone.model';

@Injectable({
    providedIn: 'root'
})
export class GatewayService {
    constructor(private http: HttpClient) {}

    getTimeZones(): Observable<TimeZone[]> {
        return this.http.get<TimeZone[]>(SERVER_API_URL + '/api/gateway/timezones');
    }
}
