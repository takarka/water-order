import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Directive, ElementRef, EventEmitter, HostListener, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';
import { Subscription } from 'rxjs';
import { MessageService } from '../service/message.service';
import { MessageBox, MessageBoxButton, MessageBoxStyle } from '../shared/message-box';
import { SimpleDialogComponent } from '../components/dialog/simple-dialog/simple-dialog.component';

@Directive({
    selector: '[confirm]'
})
export class ConfirmDirective implements OnInit, OnDestroy {
    allow_outside_click = true;
    style = MessageBoxStyle.Question;
    width;
    smallSize = false;

    @Output() confirm_yes_event: EventEmitter<any> = new EventEmitter();
    @Output() confirm_no_event: EventEmitter<any> = new EventEmitter();

    @Input() confirm_message: string;

    @Input() confirm_title = 'Confirm';

    dialogRef: MatDialogRef<SimpleDialogComponent>;

    smallDialogSubscription: Subscription;

    constructor(
        private _elementRef: ElementRef,
        private dialog: MatDialog,
        private messageService: MessageService,
        private breakpointObserver: BreakpointObserver
    ) {
    }

    ngOnInit() {
        this.smallDialogSubscription = this.breakpointObserver.observe(Breakpoints.XSmall).subscribe(result => {
            this.smallSize = result.matches;
            console.log('changed size:', this.smallSize);
            let _width;
            if (this.dialogRef) {
                if (this.smallSize) {
                    _width = '100%';
                } else {
                    _width = (this.width !== undefined && this.width !== 'px') ? this.width + 'px' : '500px';
                }
                console.log('width:', _width);
                this.dialogRef.updateSize(_width);
            }
        });
    }

    ngOnDestroy() {
        this.smallDialogSubscription.unsubscribe();
    }

    @HostListener('keydown.esc')
    public onEsc() {
        this.dialogRef.close();
    }

    @HostListener('click') confirmMessage(): void {
        console.log('Small Size click:', this.smallSize, this.style);
        let _width;
        if (this.smallSize) {
            _width = '100%';
        } else {
            _width = (this.width !== undefined && this.width !== 'px') ? this.width + 'px' : '500px';
        }
        console.log('width:', _width);
        this.dialogRef = MessageBox.show(this.dialog, this.confirm_title, this.confirm_title, this.confirm_message,
            MessageBoxButton.YesNo, this.allow_outside_click, this.style, _width);

        this.dialogRef.afterClosed().subscribe(result => {
            if (result && this.confirm_yes_event && result.result === 'yes') {
                this.confirm_yes_event.emit(result);
            }
            if (result && this.confirm_no_event && result.result === 'no') {
                this.confirm_no_event.emit(result);
            }
        });
    }
}
