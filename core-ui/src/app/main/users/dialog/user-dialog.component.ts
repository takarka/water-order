import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { UserService } from '../../service/user.service';
import { User } from '../../shared/model/user.model';
import { TranslateService } from '@ngx-translate/core';
import { DialogService } from '@ms/core/components/dialog/service/dialog.service';

@Component({
    selector: 'user-dialog',
    templateUrl: './user-dialog.component.html',
    styleUrls: ['./user-dialog.component.css'],
})
export class UserDialogComponent implements OnInit {
    form: FormGroup;
    user: User;
    title: string;
    currencies: any;


    constructor(
        private service: UserService,
        private fb: FormBuilder,
        private dialogService: DialogService,
        private translate: TranslateService,
        public dialogRef: MatDialogRef<UserDialogComponent>,
        @Inject(MAT_DIALOG_DATA) private data: any
    ) {
        console.log(data.country);
        this.user = data && data.bank ? data.bank : new User();
        if (this.user.id) {
            this.title = 'GENERAL.FIELD.BANK_ACCOUNT';
        } else {
            this.title = 'BANK_ACCOUNT.TITLE.NEW';
        }
        this.form = this.createCountryForm();
    }

    ngOnInit() {

    }

    createCountryForm(): FormGroup {
        return this.fb.group({
            id: [this.user.id],
            // name: [
            //     this.bankAccount.name,
            //     [Validators.required, notBlankValidator()],

            // ],
            // iban: [this.bankAccount.iban, [Validators.required, notBlankValidator()],
            // ],
            // integrationCode: [this.bankAccount.integrationCode, [Validators.required, notBlankValidator()],
            // ],
            // currency: [this.bankAccount.currency, [Validators.required]],
            // active: [this.bankAccount.active],
            // schoolId: [this.bankAccount.schoolId]
        });
    }

    save() {
        const bankAccount: User = this.form.value;

        // if (bankAccount.id && bankAccount.id !== '') {
        //     this.service.update(bankAccount).subscribe(
        //         e => {
        //             const item = this.translate.instant('GENERAL.FIELD.BANK_ACCOUNT');
        //             const message = this.translate.instant('GENERAL.MESSAGE.SUCCESS.UPDATED', { item: item });
        //             this.dialogService.success(message);
        //             this.dialogRef.close({ action: 'save' });
        //         },
        //         err => {
        //             this.dialogService.error(err.error.messageDetail);
        //         }
        //     );
        // } else {
        //     bankAccount.id = null;
        //     this.service.create(bankAccount).subscribe(
        //         e => {
        //             const item = this.translate.instant('GENERAL.FIELD.BANK_ACCOUNT');
        //             const message = this.translate.instant('GENERAL.MESSAGE.SUCCESS.CREATED', { item: item });
        //             this.dialogService.success(message);
        //             this.dialogRef.close({ action: 'save' });
        //         },
        //         err => {
        //             this.dialogService.error(err.error.messageDetail);
        //         }
        //     );
        // }
    }

    close() {
        this.dialogRef.close();
    }
}
