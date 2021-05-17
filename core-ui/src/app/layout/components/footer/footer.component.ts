import {
    Component, OnInit, ViewChild, ViewContainerRef, 
    ChangeDetectorRef, AfterViewChecked, OnDestroy, 
    Injector,
    NgModuleFactoryLoader,
    NgModuleFactory,
    ChangeDetectionStrategy
} from '@angular/core';
import { Subscription } from 'rxjs';

import { ViewState } from '@ms/core/shared/viewstate';
import { ButtonsBarService } from '@ms/core';

@Component({
    selector: 'footer',
    templateUrl: './footer.component.html',
    styleUrls: ['./footer.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class FooterComponent implements OnInit, OnDestroy, AfterViewChecked {
    @ViewChild('content', { read: ViewContainerRef }) content: ViewContainerRef;
    state: ViewState;
    private contentSubs: Subscription;

    constructor(
        private service: ButtonsBarService,
        private changeDetector: ChangeDetectorRef,
        private loader: NgModuleFactoryLoader,
        private injector: Injector
    ) {
    }

    ngOnInit() {
        // Lazy loading dynamic component create
         this.contentSubs = this.service.content.subscribe(comp => {
            console.log('footer => button bar create ...');
            this.content.clear();
            const path = comp.path;
            this.loader.load(path).then((moduleFactory: NgModuleFactory<any>) => {
                const moduleRef = moduleFactory.create(this.injector);
                const compFactory = moduleRef.componentFactoryResolver.resolveComponentFactory(comp.component);
                this.content.createComponent(compFactory);
                this.changeDetector.detectChanges();
            });
        });
/*         this.contentSubs = this.service.getContent().subscribe(comp => {
            this.content.clear();
            const _component = this.resolver.resolveComponentFactory(comp.component);
            this.content.createComponent(_component);
            this.changeDetector.detectChanges();
        });
 */
        this.service.changeViewState(ViewState.EDIT);
    }

    ngAfterViewChecked() {
        this.changeDetector.detectChanges();
    }

    ngOnDestroy() {
        this.changeDetector.detach();
        this.contentSubs.unsubscribe();
    }
}

