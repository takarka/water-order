import { Component, OnInit, OnDestroy } from '@angular/core';
import { ButtonsBarService } from '@ms/core';
import { AbstractButtonBar } from '../abstract-button-bar.component';

@Component({
    selector: 'empty-buttons[style=width:100%;]',
    template: ``
})
export class EmptyButtonsComponent extends AbstractButtonBar implements OnInit, OnDestroy {

    constructor(
        private buttonBarService: ButtonsBarService
    ) {
        super();
        console.log('Empty Button bar');
    }
  
    ngOnInit() {
        super.init(this.buttonBarService);
    }
}
