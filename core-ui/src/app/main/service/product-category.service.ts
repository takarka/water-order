import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../shared/model/order.model';
import { ProductCategorySearch } from '../shared/model/product-category.model';
import { ProductCategory } from '../shared/model/product.model';

@Injectable({
  providedIn: 'root',
})
export class ProductCategoryService {
  baseUrl = '/api/product-category';

  constructor(private http: HttpClient) {
    console.log('Order service created');
  }

  search(searchDTO: ProductCategorySearch): Observable<HttpResponse<ProductCategory[]>> {
    return this.http.post<Order[]>(this.baseUrl + '/search', searchDTO, { observe: 'response' });
  }
}
