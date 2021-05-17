import {
    Component,
    ContentChildren,
    QueryList,
    AfterContentInit,
} from '@angular/core';
import { MsDialogTitleDirective, MsDialogContentDirective, MsDialogActionsDirective } from './dialog.directive';


@Component({
    selector: 'ms-dialog',
    templateUrl: './dialog.component.html',
    styleUrls: ['./dialog.component.scss'],
})
export class MsDialogComponent implements AfterContentInit {
    @ContentChildren(MsDialogTitleDirective)
    dialogTitle: QueryList<MsDialogTitleDirective>;
    @ContentChildren(MsDialogContentDirective)
    dialogContent: QueryList<MsDialogContentDirective>;
    @ContentChildren(MsDialogActionsDirective)
    dialogActions: QueryList<MsDialogActionsDirective>;

    ngAfterContentInit(): void {
        if (this.dialogTitle.length > 1) {
            throw new Error(
                'Duplicate td-dialog-title component at in td-dialog.'
            );
        }
        if (this.dialogContent.length > 1) {
            throw new Error(
                'Duplicate td-dialog-content component at in td-dialog.'
            );
        }
        if (this.dialogActions.length > 1) {
            throw new Error(
                'Duplicate td-dialog-actions component at in td-dialog.'
            );
        }
    }
}
