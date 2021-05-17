import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSnackBar, MatSort, MatTableDataSource } from '@angular/material';
import { FuseProgressBarService } from '@fuse/components/progress-bar/progress-bar.service';
import { ButtonsBarService } from '@ms/core';
import { EmptyButtonsComponent } from 'app/layout/components/buttonbar/empty/empty-buttons.component';
import { ProductCategoryService } from '../service/product-category.service';
import { Order } from '../shared/model/order.model';
import { ProductCategorySearch } from '../shared/model/product-category.model';
import { product_category_module_path } from '../shared/module';
import { DialogService } from '@ms/core/components/dialog/service/dialog.service';

@Component({
  selector: 'product-category-list',
  templateUrl: './product-category.component.html',
  styleUrls: ['./product-category.component.scss'],
})
export class ProductCategoryComponent implements OnInit, AfterViewInit {
  dataSource = new MatTableDataSource<any>();
  displayedColumns = ['id', 'name'];
  searchDTO: ProductCategorySearch;
  @ViewChild(MatSort)
  sort: MatSort;
  @ViewChild(MatPaginator)
  paginator: MatPaginator;

  resultsLength = 0;

  orders: Order[] = [];

  constructor(
    private service: ProductCategoryService,
    public snackBar: MatSnackBar,
    private buttonBarService: ButtonsBarService,
    private progressService: FuseProgressBarService,
    private _dialogService: DialogService
  ) {
    this.buttonBarService.addContent({
      path: product_category_module_path,
      component: EmptyButtonsComponent,
    });
  }

  ngOnInit(): void {
    this.searchDTO = new ProductCategorySearch();
    this.doSearch();
  }

  doSearch() {
    this.progressService.show();
    this.service.search(this.searchDTO).subscribe(result => {
      this.progressService.hide();
      this.dataSource.data = result.body.slice();
      this.resultsLength = this.dataSource.data.length;
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

  doDelete(id) {
    alert('TODO: Delete Action');
  }
}
