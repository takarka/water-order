
import { Component, Input, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
    selector: 'simple-dialog-title',
    templateUrl: 'simple-dialog-title.component.html'
})
export class SimpleDialogTitleComponent implements OnInit {
    @Input() title: string;
    @Input() dialogRef: MatDialogRef<any>;
    constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

    ngOnInit() {
    }
    
 } 
