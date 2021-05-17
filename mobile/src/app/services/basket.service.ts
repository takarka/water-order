import { Injectable } from '@angular/core';
import { Product } from './product.service';
import { Order, OrderDetail, OrderTime, Address } from './order.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { API_BASE_URL } from '../constants/urls';

@Injectable()
export class BasketService {
  private deliveryTimeURL = API_BASE_URL + '/api/order-time-range';
  private userAddressURL = API_BASE_URL + '/api/address/my';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private order: Order;
  constructor(private http: HttpClient) {
    this.resetBasket();
  }

  addProduct(product: Product, quantity: number = 1) {
    let orderDetail = new OrderDetail();
    orderDetail.price = product.price;
    orderDetail.product = product;
    orderDetail.quantity = quantity;
    orderDetail.totalPrice = product.price * quantity;
    this.order.totalQuantity += quantity;
    this.order.totalPrice += orderDetail.totalPrice;
    this.order.details.push(orderDetail);
  }

  getDeliveryTime() {
    return this.http.get<OrderTime[]>(this.deliveryTimeURL, {
      headers: this.headers,
      observe: 'response',
    });
  }

  getDeliveryTimeById(id: string){
    return this.http.get<OrderTime>(this.deliveryTimeURL + '/' + id, {
      headers: this.headers,
      observe: 'response',
    });
  }

  getUserAddresses(): any {
    return this.http.get<Address[]>(this.userAddressURL, {
      headers: this.headers,
      observe: 'response',
    });
  }
  removeProduct(product: Product) {
    let removedDetails = this.order.details.filter(d => d.product.id === product.id);
    if (removedDetails.length > 0) {
      const totalRemovedQuantity = removedDetails.map(d => (d.quantity ? d.quantity : 0)).reduce((a, b) => a + b, 0);
      const totalRemovedPrice = removedDetails.map(d => (d.totalPrice ? d.totalPrice : 0)).reduce((a, b) => a + b, 0);
      this.order.details = this.order.details.filter(d => d.product.id !== product.id);
      this.order.totalQuantity = -totalRemovedQuantity;
      this.order.totalPrice = -totalRemovedPrice;
    }
  }

  resetBasket() {
    this.order = new Order();
  }

  get basket(): Order {
    return { ...this.order };
  }

  set basket(order: Order) {
    this.order = order;
  }
}
