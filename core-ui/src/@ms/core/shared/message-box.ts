import { MatDialog, MatDialogRef } from '@angular/material';
import { SimpleDialogComponent } from '../components/dialog/simple-dialog/simple-dialog.component';

export class MessageBox {
    static show(dialog: MatDialog, message, title = 'Alert',
        information = '', button = 0, allow_outside_click = false,
        style = 0, width = '200px'): MatDialogRef<SimpleDialogComponent, any> {
        const dialogRef = dialog.open(SimpleDialogComponent, {
            data: {
                title: title || 'Alert',
                message: message,
                information: information,
                button: button || 0,
                style: style || 0,
                allow_outside_click: allow_outside_click || false
            },
            width: width,
            maxWidth: 'none'
        });
        return dialogRef;
    }
}

export enum MessageBoxButton {
    Ok = 0,
    OkCancel = 1,
    YesNo = 2,
    AcceptReject = 3
}

export enum MessageBoxStyle {
    Simple = 0,
    Full = 1,
    Warning = 2,
    Information = 3,
    Failure = 4,
    Success = 5,
    Question = 6
}
