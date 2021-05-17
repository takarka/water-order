import { Injectable, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { ButtonsBarService, ViewState } from '@ms/core';

@Injectable({
    providedIn: 'root'
})
export class UserUIService implements OnDestroy {
    private _subjectNew = new Subject<Event>();
    private _subjectSave = new Subject<Event>();
    private _subjectDelete = new Subject<Event>();
    private _subjectCancel = new Subject<Event>();

    constructor(private buttonBarService: ButtonsBarService) {
        console.log('User UI service created');
    }

    // UI Actions
    // New
    doNew(event: Event) {
        this._subjectNew.next(event);
        this.buttonBarService.changeViewState(ViewState.NEW);
    }

    get new() {
        return this._subjectNew;
    }

    // Delete
    doDelete(event: Event) {
        this._subjectDelete.next(event);
        this.buttonBarService.changeViewState(ViewState.NEW);
    }

    get delete() {
        return this._subjectDelete;
    }

    // Save
    doSave(event: Event) {
        this._subjectSave.next(event);
    }

    get save() {
        return this._subjectSave;
    }

    // Cancel
    doCancel(event: Event) {
        this._subjectCancel.next(event);
        this.buttonBarService.changeViewState(ViewState.NEW);
    }

    get cancel() {
        return this._subjectCancel;
    }

    ngOnDestroy() {
        console.log('School UI service destroyed !');
        // Clean subscriptions, intervals, etc
        this._subjectNew.unsubscribe();
        this._subjectSave.unsubscribe();
        this._subjectDelete.unsubscribe();
        this._subjectCancel.unsubscribe();
    }

    setState(state) {
        this.buttonBarService.changeViewState(state);
    }

}
