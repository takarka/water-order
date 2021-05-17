import { Injectable } from "@angular/core";

import { Item } from "./item";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { API_BASE_URL } from "../constants/urls";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "root"
})
export class ItemService {

    private url = API_BASE_URL + '/api/city';

    constructor(private http: HttpClient){}
    private items = []
    getItems(): Observable<Item[]> {
        return this.http.get<Item[]>(this.url);
    }

    getItem(id: number): Item {
        return this.items.filter(item => item.id === id)[0];
    }

    create(value: any): Observable<HttpResponse<Item>> {
        return this.http.post<Item>(this.url, value, {
            observe: 'response',
        });
    }
}
