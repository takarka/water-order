import { SelectionModel } from '@angular/cdk/collections';
import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSnackBar, MatSort, MatTableDataSource } from '@angular/material';
import { FuseProgressBarService } from '@fuse/components/progress-bar/progress-bar.service';
import { ButtonsBarService } from '@ms/core';
import { Subscription } from 'rxjs';
import { OrderService } from '../service/order.service';
import { Address } from '../shared/model/address.model';
import { Order, OrderSearch, OrderStatus } from '../shared/model/order.model';
import { order_module_path } from '../shared/module';
import { enumValues } from '../shared/utils';
import { OrdersButtonsComponent } from './buttons/buttons.component';
import { OrderUIService } from './order-ui.service';
import { DialogService } from '@ms/core/components/dialog/service/dialog.service';
import { PrincipalService } from '@ms/core/auth/principal.service';
import { UserService } from '../service/user.service';
import { User } from '../shared/model/user.model';

@Component({
  selector: 'order-list',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss'],
})
export class OrderComponent implements OnInit, AfterViewInit, OnDestroy {
  dataSource = new MatTableDataSource<any>();
  displayedColumns = [
    'select',
    'id',
    'orderNumber',
    'estimateDeliveryTime',
    'deliveryAddress',
    'phone',
    'totalQuantity',
    'totalPrice',
    'manufactureCancel',
    'status',
  ];
  searchDTO: OrderSearch;
  @ViewChild(MatSort)
  sort: MatSort;
  @ViewChild(MatPaginator)
  paginator: MatPaginator;

  resultsLength = 0;

  orders: Order[] = [];
  orderStatusKeys: any[];
  OrderStatus = OrderStatus;
  selection = new SelectionModel<Order>(true, []);
  _cancelSubcription: Subscription;
  _approveSubscription: Subscription;
  isAdmin: boolean;

  user: User;

  constructor(
    private service: OrderService,
    public snackBar: MatSnackBar,
    private buttonBarService: ButtonsBarService,
    private progressService: FuseProgressBarService,
    private orderUIService: OrderUIService,
    private _dialogService: DialogService,
    private principalService: PrincipalService,
    private userService: UserService
  ) {
    this.buttonBarService.addContent({
      path: order_module_path,
      component: OrdersButtonsComponent,
    });
    this._approveSubscription = this.orderUIService.approve.subscribe(ids => {
      this.doApprove(ids);
    });
    this._cancelSubcription = this.orderUIService.cancel.subscribe(ids => {
      this.doCancel(ids);
    });

    this.userService.me().subscribe(res => {
      this.user = res.body;

      this.isAdmin = this.user.roles.find(x => x === 'ADMIN') === 'ADMIN';
      console.log('isAdmin:', this.isAdmin);
    });

    console.log('isAdmin2:', this.isAdmin);

    // this.principalService.identity().then(account => {
    //   console.log('account', account);
    // });
    // this.principalService.hasAuthority('ADMIN').then(res => (this.isAdmin = res));
    // Navigation bar title change
    // navigationTitle.next({title: 'SCHOOL.TITLE.BROWSER'});
  }

  ngOnInit(): void {
    this.orderStatusKeys = enumValues(this.OrderStatus);
    const initialSelection = [];
    const allowMultiSelect = true;
    this.selection = new SelectionModel<Order>(allowMultiSelect, initialSelection);
    this.searchDTO = new OrderSearch();
    this.doSearch();
  }

  doSearch() {
    this.progressService.show();
    this.service.searchOrders(this.searchDTO).subscribe(result => {
      this.progressService.hide();
      this.dataSource.data = result.body.slice();
      this.resultsLength = this.dataSource.data.length;
      this.selection.clear();
      this.orderUIService.itemSelectedSubject.next(this.selection);
    });
  }

  doApprove(ids: String[]) {
    this.service.approve(ids).subscribe(res => {
      this.doSearch();
    });
  }

  doCancel(ids: String[]) {
    this.service.cancel(ids).subscribe(res => {
      this.doSearch();
    });
  }

  ngAfterViewInit(): void {
    // this.dataSource.sortingDataAccessor = (order, property) => {
    //     switch (property) {
    //         case 'country_name':
    //             return school.address.country
    //                 ? school.address.country.name
    //                 : '';
    //         case 'city_name':
    //             return school.address.city ? school.address.city.name : '';
    //         default:
    //             return school[property];
    //     }
    // };
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  getDeliveryAddress(address: Address) {
    let res = '';
    if (address) {
      if (address.street) {
        res = res + address.street;
      }
      if (address.buildingNumber) {
        res = res + ', ' + address.buildingNumber;
      }
    }
    return res;
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ? this.selection.clear() : this.dataSource.data.forEach(row => this.selection.select(row));
  }

  onSelectAll(event: Event) {
    this.masterToggle();
    this.orderUIService.itemSelectedSubject.next(this.selection);
  }

  onSelectionChange(row: Order) {
    this.selection.toggle(row);
    console.log('Row selection change !');
    this.orderUIService.itemSelectedSubject.next(this.selection);
  }

  ngOnDestroy(): void {
    if (this._approveSubscription) {
      this._approveSubscription.unsubscribe();
    }
    if (this._cancelSubcription) {
      this._cancelSubcription.unsubscribe();
    }
  }
}
