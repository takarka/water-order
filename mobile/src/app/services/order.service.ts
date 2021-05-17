import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Product } from './product.service';
import { API_BASE_URL } from '../constants/urls';
import { PropertyConverter } from 'nativescript-ui-dataform';

@Injectable()
export class OrderService {
  private url = API_BASE_URL + '/api/order';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  order: Order;
  constructor(private http: HttpClient) { }

  post(order: Order) {
    return this.http.post<Order>(`${this.url}/`, order, {
      headers: this.headers,
    });
  }

  searchOrders(searchDTO) {
    return this.http.post<Order[]>(this.url + '/search', searchDTO, {
      headers: this.headers,
      observe: 'response',
    });
  }

  create(order: Order) {
    return this.http.post<Order>(this.url, order, {
      headers: this.headers,
      observe: 'response',
    });
  }

  getAll() {
    return this.http.get<Order[]>(this.url, {
      headers: this.headers,
      observe: 'response',
    });
  }
  getMyOrders() {
    return this.http.get<Order[]>(this.url + '/my', {
      headers: this.headers,
      observe: 'response',
    });
  }

  deleteById(orderId: string) {
    return this.http.delete(this.url + '/' + orderId, {
      headers: this.headers,
      observe: 'response',
    });
  }

  getById(orderId: string) {
    return this.http.get<Order>(this.url + '/' + orderId, {
      headers: this.headers,
      observe: 'response',
    });
  }

  get selectedOrder() {
    return this.order;
  }

  set selectedOrder(order: Order) {
    this.order = order;
  }
}
export class Order {
  constructor(
    public id?: string,
    public orderNumber?: string,
    public orderDate?: Date,
    public totalQuantity?: number,
    public phone?: string,
    public totalPrice?: number,
    public deliveryAddress?: Address,
    public feedback?: string,
    public comment?: string,
    public orderTimeRange?: OrderTime,
    public details?: OrderDetail[],
    public status?: string,
    public userName?: string,
    public itemType?: string // for showing history
  ) {
    this.id = id ? id : null;
    this.orderNumber = orderNumber ? orderNumber : '';
    this.orderDate = orderDate ? orderDate : new Date();
    this.totalQuantity = totalQuantity ? totalQuantity : 0;
    this.phone = phone ? phone : '';
    this.totalPrice = totalPrice ? totalPrice : 0;
    this.deliveryAddress = deliveryAddress ? deliveryAddress : new Address();
    this.feedback = feedback ? feedback : '';
    this.comment = comment ? comment : '';
    this.orderTimeRange = orderTimeRange ? orderTimeRange : new OrderTime();
    this.details = details ? details : [];
  }
}
export class OrderViewModel {
  public date: Date = new Date();
  public time: string = '';
  public address: string = '';
  public comment: string = '';
}
export class OrderTime {
  id: string;
  name: string;
  start: string;
  end: string;
}
export class OrderDetail {
  product: Product;
  price: number = 0;
  quantity: number = 0;
  totalPrice: number = 0;
}

export class Address {
  id: string;
  name: string = '';
  city: City;
  region: Region;
  street: string;
  buildingNumber: string;
  entranceNumber: string;
  floor: string;
  entranceCode: string;
  flatNumber: string;
}

export class AddressForm {
  constructor(
    public name?: string,
    public city?: string,
    public region?: string,
    public street?: string,
    public buildingNumber?: string,
    public floor?: string,
    public entranceCode?: string,
    public flatNumber?: string
  ) {
    this.name = name ? name : null;
    this.city = city ? city : null;
    this.region = region ? region : null;
    this.street = street ? street : null;
    this.buildingNumber = buildingNumber ? buildingNumber : null;
    this.floor = floor ? floor : null;
    this.entranceCode = entranceCode ? entranceCode : null;
    this.flatNumber = flatNumber ? flatNumber : null;
  }
}

export class City {
  constructor(public id?: string, public name?: string) {
    this.id = id ? id : null;
    this.name = name ? name : null;
  }
}

export class Region {
  constructor(public id?: string, public name?: string) {
    this.id = id ? id : null;
    this.name = name ? name : null;
  }
}

export enum OrderStatus {
  CREATED = "CREATED",
  APPROVED = "APPROVED",
  CANCELED = "APPROVED",
  DELIVERING = "DELIVERING",
  ORDERING = "ORDERING"
}
