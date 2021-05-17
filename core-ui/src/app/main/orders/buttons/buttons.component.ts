import { Component, OnDestroy, OnInit } from '@angular/core';
import { ButtonsBarService } from '@ms/core';
import { AbstractButtonBar } from 'app/layout/components/buttonbar/abstract-button-bar.component';
import { Order, OrderStatus } from 'app/main/shared/model/order.model';
import { Subscription, Observable } from 'rxjs';
import { OrderUIService } from '../order-ui.service';
import { enumType } from 'app/main/shared/utils';

@Component({
  selector: 'orders-buttons[style=width:100%;]',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.scss'],
})
export class OrdersButtonsComponent extends AbstractButtonBar implements OnInit, OnDestroy {

  itemCreatedSelected = false;
  itemApprovedSelected = false;
  selectedItems: Order[];
  // isSelected: boolean;
  itemSelectSubscription: Subscription;

  constructor(private service: OrderUIService, private buttonBarService: ButtonsBarService) {
    super();
    console.log('Button bar');
  }


  ngOnInit() {
    super.init(this.buttonBarService);
    // this.itemSelectSubscription = this.service.itemSelected.subscribe((event: Event) => {
    //   this.isSelected = event ? true : false;
    // });
    this.itemSelectSubscription = this.service.itemSelectedSubject.subscribe(r => {
      this.itemCreatedSelected = false;
      this.selectedItems = r.selected;
      let isCreated = r.selected.length !== 0;
      let isApproved = r.selected.length !== 0;
      r.selected.forEach(s => {
        // Only created
        if (s.status !== enumType(OrderStatus.CREATED, OrderStatus)){
          isCreated = false;
        }
        if (!(s.status === enumType(OrderStatus.APPROVED, OrderStatus) || s.status === enumType(OrderStatus.CREATED, OrderStatus))){
          isApproved = false;
        }
      });
      this.itemCreatedSelected = isCreated;
      this.itemApprovedSelected = isApproved;
    });
  }


  doApprove(event: Event) {
    const selectedIds = [];
    this.selectedItems.forEach(order => selectedIds.push(order.id));
    this.service.doApprove(selectedIds);
  }

  doCancel(event: Event) {
    const selectedIds = [];
    this.selectedItems.forEach(order => selectedIds.push(order.id));
    this.service.doCancel(selectedIds);
  }


  ngOnDestroy() {
    super.ngOnDestroy();
    if (this.itemSelectSubscription) {
      this.itemSelectSubscription.unsubscribe();
    }
  }
}
