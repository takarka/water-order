import { Component, OnInit, OnDestroy } from '@angular/core';
import { ButtonsBarService } from '@ms/core';
import { UserUIService } from '../user-ui.service';
import { AbstractButtonBar } from 'app/layout/components/buttonbar/abstract-button-bar.component';


@Component({
    selector: 'user-form-buttons[style=width:100%;]',
    templateUrl: './buttons.component.html',
    styleUrls: ['./buttons.component.scss'],
})
export class UserFormButtonsComponent extends AbstractButtonBar
    implements OnInit {
    constructor(
        private serviceUI: UserUIService,
        private buttonBarService: ButtonsBarService,
    ) {
        super();
        console.log('User Button bar');
    }

    ngOnInit() {
        super.init(this.buttonBarService);
    }

    doNew(event: Event) {
        this.serviceUI.doNew(event);
    }

    doSave(event: Event) {
        this.serviceUI.doSave(event);
    }

    doDelete(event: Event) {
        this.serviceUI.doDelete(event);
    }

}
