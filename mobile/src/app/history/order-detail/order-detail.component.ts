import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RouterExtensions } from 'nativescript-angular/router';
import { Order, OrderService, OrderTime } from '~/app/services/order.service';
import { BasketService } from '~/app/services/basket.service';

@Component({
  selector: 'ns-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css'],
  moduleId: module.id,
})
export class OrderDetailComponent implements OnInit {
  selectedOrder: Order;
  public orderTimeRange = '';

  constructor(private service: OrderService,
    private basketService: BasketService,
    private routerExtension: RouterExtensions,
    private activeRoute: ActivatedRoute) {
  }

  ngOnInit() {
    console.log("Detail");
    this.selectedOrder = this.service.selectedOrder;

    if (this.selectedOrder && this.selectedOrder.orderTimeRange) {
      this.orderTimeRange = this.selectedOrder.orderTimeRange.start + ' - ' + this.selectedOrder.orderTimeRange.end;
    }
  }

  goBack() {
    this.routerExtension.back({ relativeTo: this.activeRoute });
  }

  repeatOrder() {
    this.basketService.resetBasket();
    this.selectedOrder.id = null;
    this.basketService.basket = this.selectedOrder;
    this.routerExtension.navigate(['/basket']);
  }

  getProductTotalPrice() {
    return this.selectedOrder && this.selectedOrder.details[0] ? this.selectedOrder.details[0].totalPrice + ' KZT' : '';
  }

  getProductQuantity() {
    return this.selectedOrder && this.selectedOrder.details[0] ? this.selectedOrder.details[0].quantity + ' шт.' : '';
  }

  getProductPrice() {
    return this.selectedOrder && this.selectedOrder.details[0] ? this.selectedOrder.details[0].price + ' KZT' : '';
  }

  getProductName() {
    return this.selectedOrder && this.selectedOrder.details[0] && this.selectedOrder.details[0].product
      ? this.selectedOrder.details[0].product.name
      : '2';
  }

  getOrderDate() {
    return this.selectedOrder ? this.selectedOrder.orderDate + ' : ' + this.getOrderTimeRange() : '';
  }

  getOrderTimeRange(){
    return this.orderTimeRange ? this.orderTimeRange : '';
  }

  getdDeliveryAddress() {
    let address = '';
    if (this.selectedOrder && this.selectedOrder.deliveryAddress) {
      address += this.selectedOrder.deliveryAddress.city ? this.selectedOrder.deliveryAddress.city.name + ', ' : '';
      address += this.selectedOrder.deliveryAddress.street ? this.selectedOrder.deliveryAddress.street + ', ' : '';
      address += this.selectedOrder.deliveryAddress.buildingNumber
        ? this.selectedOrder.deliveryAddress.buildingNumber
        : '';
    }
    return address;
  }
}
