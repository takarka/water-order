import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSnackBar, MatSort, MatTableDataSource, MatDialog } from '@angular/material';
import { FuseProgressBarService } from '@fuse/components/progress-bar/progress-bar.service';
import { ButtonsBarService } from '@ms/core';
import { DialogService } from '@ms/core/components/dialog/service/dialog.service';
import { EmptyButtonsComponent } from 'app/layout/components/buttonbar/empty/empty-buttons.component';
import { OrderTimeRange } from '../shared/model/time-range.model';
import { order_module_path } from '../shared/module';
import { TimeRangeDialogComponent } from './dialog/time-range-dialog.component';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { OrderTimeRangeService } from '../service/order-time-range.service';

@Component({
    selector: 'order-time-range-list',
    templateUrl: './order-time-range.component.html',
    styleUrls: ['./order-time-range.component.scss']
})
export class OrderTimeRangeComponent implements OnInit, AfterViewInit, OnDestroy {

    dataSource = new MatTableDataSource<any>();
    displayedColumns = [
        'id',
        'start',
        'end',
        'order',
        'buttons'
    ];

    @ViewChild(MatSort)
    sort: MatSort;
    @ViewChild(MatPaginator)
    paginator: MatPaginator;

    resultsLength = 0;

    timeRanges: OrderTimeRange[] = [];

    deleteTitle = 'Удалить временной интервал';
    deleteConfirmMessage = 'Вы уверены, что хотите удалить этот временной интервал:';

    constructor(
        public snackBar: MatSnackBar,
        private buttonBarService: ButtonsBarService,
        private progressService: FuseProgressBarService,
        private dialogService: DialogService,
        private dialog: MatDialog,
        private service: OrderTimeRangeService,
        private breakpointObserver: BreakpointObserver,
    ) {
        this.buttonBarService.addContent({
            path: order_module_path,
            component: EmptyButtonsComponent,
        });
        // Navigation bar title change
        // navigationTitle.next({title: 'SCHOOL.TITLE.BROWSER'});
    }

    ngOnInit(): void {
        this.doSearch();
    }

    doSearch() {
        this.progressService.show();
        this.service.getAll().subscribe(result => {
            console.log(result);
            this.progressService.hide();
            this.dataSource.data = result.body.slice();
            this.resultsLength = this.dataSource.data.length;
        });
    }


    ngAfterViewInit(): void {
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
    }

    doEdit(row): void {
        this.createDialog(row);
    }

    doDelete(row): void {
        this.progressService.show();
        this.service.delete(row).subscribe(e => {
            this.progressService.hide();
            this.doSearch();
            this.dialogService.success('Временной интервал успешно удален');
        });
    }

    doNew(): void {
        this.createDialog(new OrderTimeRange());
    }

    createDialog(row) {
        const dialogRef = this.dialog.open(TimeRangeDialogComponent, {
            width: '500px',
            maxWidth: 'none',
            autoFocus: true,
            disableClose: false,
            data: {
                orderTimeRange: { ...row },
            },
        });

        const smallDialogSubscription = this.breakpointObserver.observe(Breakpoints.Handset).subscribe(result => {
            if (result.matches) {
                dialogRef.updateSize('100%', '100%');
            } else {
                dialogRef.updateSize('500px', '300px');
            }
        });

        dialogRef.afterClosed().subscribe(response => {
            if (response && response.action === 'save') {
                this.doSearch();
            }
            smallDialogSubscription.unsubscribe();
        });
    }


    ngOnDestroy(): void {

    }
}
