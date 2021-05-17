import { Injectable, Injector, OnDestroy } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import { Type } from '@angular/compiler/src/core';
import { ViewState } from '../shared/viewstate';

export interface ComponentType {
    path: string;
    component: Type;
}

@Injectable({
    providedIn: 'root'
})
export class ButtonsBarService implements OnDestroy {
    private _content = new Subject<ComponentType>();
    private _subjectState = new Subject<ViewState>();

    constructor(private injector: Injector) { 
        console.log('ButtonsBarService created');
    }

    // Add Normal buttons group to button bar
    addContent(c: ComponentType) {
        this._content.next(c);
    }

    get content() {
        return this._content;
    }

    // ViewState
    changeViewState(state: ViewState) {
        this._subjectState.next(state);
    }

    get viewState() {
        return this._subjectState;
    }

    ngOnDestroy() {
        this._content.unsubscribe();
        this._subjectState.unsubscribe();
    }

}
