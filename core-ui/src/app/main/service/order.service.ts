import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { OrderSearch, Order } from '../shared/model/order.model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class OrderService {

    baseUrl = '/api/order';

    constructor(
        private http: HttpClient
    ) {
        console.log('Order service created');
    }

    searchOrders(
        searchDTO: OrderSearch
    ): Observable<HttpResponse<Order[]>> {
        return this.http.post<Order[]>(
            this.baseUrl + '/search',
            searchDTO,
            { observe: 'response' }
        );
    }

    approve(ordersIds: String[]): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.baseUrl + '/approve',
            ordersIds,
            { observe: 'response' });
    }

    cancel(ordersIds: String[]): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.baseUrl + '/cancel',
            ordersIds,
            { observe: 'response' });
    }

}
