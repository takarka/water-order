import { Component, Input, TemplateRef, ChangeDetectionStrategy } from '@angular/core';
import { ButtonsBarService } from '@ms/core';
import { Location } from '@angular/common';

@Component({
    selector: 'button-bar',
    templateUrl: './buttonbar.component.html',
    styleUrls: ['./buttonbar.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class ButtonBarComponent {

    @Input() mobilButtons: TemplateRef<any>; 
    @Input() normalButtons: TemplateRef<any>;
    @Input() dangerButtons: TemplateRef<any>;

    constructor(private buttonBarService: ButtonsBarService, private location: Location) {}

    doBack(event: any) {
        this.location.back();
    }


}
