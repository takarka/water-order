import { Component, OnInit, ViewChild } from '@angular/core';
import { BasketService } from '../services/basket.service';
import { Order, OrderService, Address, OrderViewModel, OrderTime } from '../services/order.service';
import { RouterExtensions } from 'nativescript-angular/router';
import { ActivatedRoute } from '@angular/router';
import { RadDataFormComponent } from 'nativescript-ui-dataform/angular/dataform-directives';
import * as Toast from 'nativescript-toast';

@Component({
  selector: 'ns-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css'],
  moduleId: module.id,
})
export class BasketComponent implements OnInit {
  basket: Order;
  orderA = new OrderViewModel();
  @ViewChild('basketFrom') dataFormComponent: RadDataFormComponent;

  public times: any;
  public adderesses: any;
  selectedTime = 0;
  selectedAddress = 0;
  comment: string;
  constructor(
    private service: BasketService,
    private orderService: OrderService,
    private routerExtension: RouterExtensions,
    private activeRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.basket = this.service.basket;
    this.service.getDeliveryTime().subscribe(result => {
      const orderTimes = result.body.map(orderTime => {
        return {
          id: orderTime.id,
          name: orderTime.start + ' - ' + orderTime.end,
        };
      });
      this.times = {
        key: 'id',
        label: 'name',
        items: orderTimes,
      };
    });
    this.service.getUserAddresses().subscribe(result => {
      this.adderesses = {
        key: 'id',
        label: 'name',
        items: result.body,
      };
    });
  }
  order() {
    this.dataFormComponent.dataForm.validateAll().then(result => {
      if (result) {
        this.basket.deliveryAddress = new Address();
        this.basket.deliveryAddress.id = this.orderA.address;
        this.basket.orderDate = this.orderA.date;
        this.basket.orderTimeRange = new OrderTime();
        this.basket.orderTimeRange.id = this.orderA.time;
        this.basket.comment = this.orderA.comment;
        console.log('basket: ', this.basket);
        this.orderService.post(this.basket).subscribe(result => {
          this.service.resetBasket();
          console.log('Got result of basked submission: ', result);
          Toast.makeText('Заказ успешно размещен').show();
          this.goBack();
        });
      }
    });
  }
  goBack() {
    this.routerExtension.back({ relativeTo: this.activeRoute });
  }
}
