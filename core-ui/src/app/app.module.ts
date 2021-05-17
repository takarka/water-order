import { NgModule, ErrorHandler, Optional, SkipSelf, Injector } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslateModule } from '@ngx-translate/core';
import { StoreModule } from '@ngrx/store';
import 'hammerjs';

import { FuseModule } from '@fuse/fuse.module';
import { FuseSharedModule } from '@fuse/shared.module';
import { FuseProgressBarModule, FuseSidebarModule, FuseThemeOptionsModule } from '@fuse/components';

import { fuseConfig } from './fuse-config';

import { AppComponent } from './app.component';
import { LayoutModule } from './layout/layout.module';
import { reducers } from './app.reducer';
import { AppRoutingModule } from './app-routing.module';
// import { TokenInterceptor } from '@ms/core/shared/token.interceptor';
import { throwIfAlreadyLoaded } from '@ms/core/shared/module-import.guard';
import { FuseDirectivesModule } from '@fuse/directives/directives';
import { CoreModule } from '@ms/core/service/core.module';
import { GlobaleErrorHandler, LoginModule } from '@ms/core';
import { Ng2Webstorage } from 'ngx-webstorage';
import { AuthExpiredInterceptor } from '@ms/core/interceptor/auth-expired.interceptor';
import { NotificationInterceptor } from '@ms/core/interceptor/notification.interceptor';
import { ErrorHandlerInterceptor } from '@ms/core/interceptor/errorhandler.interceptor';
import { JhiEventManager } from 'ng-jhipster';
import { AuthInterceptor } from '@ms/core/auth/auth.interceptor';
import { AuthService } from '@ms/core/auth/auth.service';
import { AuthenticantionGuard } from '@ms/core/auth/authentication.guard';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FuseDirectivesModule,
        FuseSharedModule,
        TranslateModule.forRoot(),
        Ng2Webstorage.forRoot({ prefix: 'mersys', separator: '-' }),
        LoginModule,
        NgxMaterialTimepickerModule.forRoot(),
        // Fuse modules
        FuseModule.forRoot(fuseConfig),
        FuseProgressBarModule,
        FuseSidebarModule,
        FuseThemeOptionsModule,
        CoreModule.forRoot(),

        // App modules
        LayoutModule,
        // FooterModule,
        // SampleModule,
        // DemoModule,


        // State Store Module
        StoreModule.forRoot(reducers),
        // Routing module
        AppRoutingModule,
    ],
    providers: [
        // {
        //     provide: HTTP_INTERCEPTORS,
        //     useClass: AuthExpiredInterceptor,
        //     multi: true,
        //     deps: [Injector]
        // },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [Injector]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [JhiEventManager]
        },
        AuthService,
        AuthenticantionGuard,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
          },
        /** 
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptor,
            multi: true,
        },
        */
        {
            provide: ErrorHandler,
            useExisting: GlobaleErrorHandler
        }
    ],
    bootstrap: [
        AppComponent
    ],
})
export class AppModule {

    constructor(@Optional() @SkipSelf() parentModule: AppModule) {
        throwIfAlreadyLoaded(parentModule, 'AppModule');
    }
}
