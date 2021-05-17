import { Directive } from '@angular/core';

@Directive({ selector: '[msDialogTitle]' })
export class MsDialogTitleDirective {
    constructor() {}
}

@Directive({ selector: '[msDialogContent]' })
export class MsDialogContentDirective {}

@Directive({ selector: '[msDialogActions]' })
export class MsDialogActionsDirective {} 