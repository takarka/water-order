import { OnDestroy } from '@angular/core';
import { ViewState } from '@ms/core/shared/viewstate';
import { Subscription } from 'rxjs';
import { ButtonsBarService } from '@ms/core';

export abstract class AbstractButtonBar implements OnDestroy {
    isMobil = false;
    state: ViewState;
    _fixed = false;
    open = false;
    spin = true;
    direction = 'up';
    animationMode = 'fling';
    stateSub: Subscription;
    constructor() { }

    init(buttonBarService: ButtonsBarService) {
        this.stateSub = buttonBarService.viewState.subscribe(value => {
            this.state = value;
        });
    }
    get fixed(): boolean {
        return this._fixed;
    }

    set fixed(fixed: boolean) {
        this._fixed = fixed;
        if (this._fixed) {
            this.open = true;
        }
    }

    public doAction(event: any) {
        console.log(event);
    }

    ngOnDestroy() {
        if (this.stateSub) {
            this.stateSub.unsubscribe();
        }
    }

}
