import { Component, OnInit } from '@angular/core';
import { RouterExtensions } from 'nativescript-angular/router';
import { NavigationService } from '../navigation.service';
import { Order, OrderService } from '../services/order.service';

@Component({
  selector: 'ns-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css'],
  moduleId: module.id,
})
export class HistoryComponent implements OnInit {
  public orders: Order[];

  itemType_HEADER = 'header';
  itemType_ITEM = 'item';

  public currentLanguage = 'en';

  constructor(
    private service: OrderService,
    private navigationService: NavigationService,
    private routerExtension: RouterExtensions,
  ) {
  }

  ngOnInit(): void {
    this.navigationService.tab.subscribe(index => {
      if (index === 2) {
        //HISTORY TAB
        console.log('HISTORY');
        this.getAllOrders();
      }
    });
  }

  onItemTap(args: any) {
    const itemData: Order = args.view.bindingContext;
    if(itemData.itemType === this.itemType_HEADER){
      return;
    }
    this.service.selectedOrder = itemData;
    this.routerExtension.navigate(['/order-detail']);
  }

  getAllOrders() {
    this.service.getMyOrders().subscribe(response => {
      this.orders = response.body.slice();
      this.sortOrders();
    });
  }

  sortOrders() {
    let orderDateMapping = new Map();
    let tempOrders = [];
    for (let order of this.orders) {
      console.log(' HISTORY - orderDate: ', order);
      if (!orderDateMapping.has(order.orderDate)) {
        orderDateMapping.set(order.orderDate, '1');
        let orderHeader = new Order();
        orderHeader.orderDate = order.orderDate;
        orderHeader.itemType = this.itemType_HEADER;
        tempOrders.push(orderHeader);
      }
      order.itemType = this.itemType_ITEM;
      tempOrders.push(order);
    }
    this.orders = tempOrders;
  }

  public templateSelector(order: Order) {
    return order ? order.itemType : '';
  }

  getProductQuantity(order: Order) {
    return order && order.details[0] ? order.details[0].quantity : '';
  }

  getProductName(order: Order) {
    console.log('Product Name: ');
    return order && order.details[0] && order.details[0].product ? order.details[0].product.name : '';
  }

  getProductTotalPrice(order: Order) {
    return order && order.details[0] ? order.details[0].totalPrice : '';
  }
}
