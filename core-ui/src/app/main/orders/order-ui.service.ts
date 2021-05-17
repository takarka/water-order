import { SelectionModel } from '@angular/cdk/collections';
import { Injectable, OnDestroy } from '@angular/core';
import { ButtonsBarService, ViewState } from '@ms/core';
import { Subject } from 'rxjs';
import { Order } from '../shared/model/order.model';

@Injectable({
  providedIn: 'root',
})
export class OrderUIService implements OnDestroy {

  private _subjectCancel = new Subject<String[]>();
  private _subjectApprove = new Subject<String[]>();
  private _subjectItemsSelected = new Subject<Event>();
  itemSelectedSubject = new Subject<SelectionModel<Order>>();

  constructor(private buttonBarService: ButtonsBarService) {
    console.log('Order UI service created');
  }

  // Cancel
  doCancel(ids: String[]) {
    this._subjectCancel.next(ids);
  }

  get cancel() {
    return this._subjectCancel;
  }

  // Approve
  get approve() {
    return this._subjectApprove;
  }

  doApprove(ids: String[]) {
    this._subjectApprove.next(ids);
  }

  get itemSelected() {
    return this._subjectItemsSelected;
  }

  doItemSelected(event: Event) {
    this._subjectItemsSelected.next(event);
  }

  ngOnDestroy() {
    this._subjectCancel.unsubscribe();
    this._subjectApprove.unsubscribe();
    this._subjectItemsSelected.unsubscribe();
    this.itemSelectedSubject.unsubscribe();
  }
}
