import { Directive, Input, ViewContainerRef, TemplateRef } from '@angular/core';

@Directive({
    selector: '[ifShow]'
})
export class IfShowDirective {
    constructor(
        private viewContainer: ViewContainerRef,
        private template: TemplateRef<any>
    ) {
    }

    @Input() set ifShow(condition) {
        if (condition) {
            this.viewContainer.createEmbeddedView(this.template);
        } else {
            this.viewContainer.clear();
        }
    }
}
