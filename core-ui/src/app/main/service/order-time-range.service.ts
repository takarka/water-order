import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderTimeRange } from '../shared/model/time-range.model';

@Injectable({
    providedIn: 'root'
})
export class OrderTimeRangeService {

    baseUrl = '/api/order-time-range';

    constructor(
        private http: HttpClient
    ) {
        console.log('OrderTimeRange service created');
    }

    getAll(): Observable<HttpResponse<OrderTimeRange[]>> {
        return this.http.get<OrderTimeRange[]>(
            this.baseUrl,
            { observe: 'response' }
        );
    }

    save(orderTimeRange: OrderTimeRange): Observable<HttpResponse<OrderTimeRange>> {
        console.log("Service: " + orderTimeRange)
        return this.http.post<OrderTimeRange>(this.baseUrl,
            orderTimeRange,
            { observe: 'response' });
    }

    update(orderTimeRange: OrderTimeRange): Observable<HttpResponse<OrderTimeRange>> {
        return this.http.put<OrderTimeRange>(this.baseUrl,
            orderTimeRange,
            { observe: 'response' });
    }

    delete(orderTimeRange: OrderTimeRange): Observable<HttpResponse<any>> {
        return this.http.delete(this.baseUrl + '/' + orderTimeRange.id, { observe: 'response' });
    }

}
