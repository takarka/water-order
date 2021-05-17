import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { User, UserSearch } from '../shared/model/user.model';
import { MatPaginator, MatTableDataSource, MatSort, PageEvent, MatDialogRef, MatDialog, MatSlideToggleChange } from '@angular/material';
import { FuseConfirmDialogComponent } from '@fuse/components/confirm-dialog/confirm-dialog.component';
import { ButtonsBarService } from '@ms/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { FuseProgressBarService } from '@fuse/components/progress-bar/progress-bar.service';
import { TranslateService } from '@ngx-translate/core';
import { EmptyButtonsComponent } from 'app/layout/components/buttonbar/empty/empty-buttons.component';
import { UserDialogComponent } from './dialog/user-dialog.component';
import { UserService } from '../service/user.service';
import { user_module_path } from '../shared/module';
import { DialogService } from '@ms/core/components/dialog/service/dialog.service';

@Component({
    selector: 'user-list',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit, AfterViewInit {

    displayedColumns: string[] = ['id', 'name', 'email', 'phone', 'active', 'buttons'];
    dataSource = new MatTableDataSource<User>();

    resultsLength = 0;
    isRateLimitReached = false;
    searchDTO: UserSearch;
    currencies: any;

    @ViewChild(MatPaginator)
    paginator: MatPaginator;

    @ViewChild(MatSort)
    sort: MatSort;

    pageEvent: PageEvent;

    confirmDialogRef: MatDialogRef<FuseConfirmDialogComponent>;

    constructor(
        private service: UserService,
        private dialog: MatDialog,
        private dialogService: DialogService,
        private breakpointObserver: BreakpointObserver,
        private progressService: FuseProgressBarService,
        private buttonBarService: ButtonsBarService,
        private translate: TranslateService,
    ) {
    }

    ngOnInit() {
        this.buttonBarService.addContent({
            path: user_module_path,
            component: EmptyButtonsComponent,
        });
        this.searchDTO = new UserSearch();
        this.doSearch();
    }

    ngAfterViewInit() {
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
    }

    doSearch() {
        this.progressService.show();
        this.service
            .search(this.searchDTO)
            .subscribe(result => {
                this.progressService.hide();
                this.dataSource.data = result.body.slice();
                this.resultsLength = this.dataSource.data.length;
            }
                , error => {
                    this.dialogService.error(error.error.messageDetail);
                });
    }

    doDelete(row): void {
        this.service.delete(row.id).subscribe(e => {
            this.doSearch();
            const item = this.translate.instant('GENERAL.FIELD.BANK_ACCOUNT');
            const message = this.translate.instant('GENERAL.MESSAGE.SUCCESS.DELETED', { item: item });
            this.dialogService.success(message);
        });
    }

    // createDialog(row) {
    //     const dialogRef = this.dialog.open(UserDialogComponent, {
    //         width: '500px',
    //         maxWidth: 'none',
    //         autoFocus: true,
    //         disableClose: false,
    //         data: {
    //             bank: { ...row }
    //         },
    //     });

    //     const smallDialogSubscription = this.breakpointObserver
    //         .observe(Breakpoints.Handset)
    //         .subscribe(result => {
    //             if (result.matches) {
    //                 dialogRef.updateSize('100%', '100%');
    //             } else {
    //                 dialogRef.updateSize('500px', '450px');
    //             }
    //         });

    //     dialogRef.afterClosed().subscribe(response => {
    //         if (response && response.action === 'save') {
    //             this.doSearch();
    //         }
    //         smallDialogSubscription.unsubscribe();
    //     });
    // }

    setActive(id: string, event: MatSlideToggleChange) {
        this.dataSource.data.forEach(bankAccount => {
            if (bankAccount.id === id) {
                const index = this.dataSource.data.indexOf(bankAccount);
                bankAccount.active = event.checked;
                this.service.update(bankAccount).subscribe(r => {
                    this.dataSource.data[index].active = r.body.active;
                    const item = this.translate.instant('GENERAL.FIELD.BANK_ACCOUNT');
                    const message = this.translate.instant('GENERAL.MESSAGE.SUCCESS.UPDATED', { item: item });
                    this.dialogService.success(message);
                });
                return;
            }
        });
    }
}
