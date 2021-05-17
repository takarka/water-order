import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import { Observable } from 'rxjs';

import { AppModule } from 'app/app.module';
import { environment } from 'environments/environment';
import { hmrBootstrap } from 'hmr';

Observable.prototype.debug = function (message: string) {
    return this.do(
        nextValue => {
            if (!environment.production) {
                console.log(message, nextValue);
            }
        },
        error => {
            if (!environment.production) {
                console.log('ERROR >>> ', message, error);
            }
        },
        () => {
            if (!environment.production) {
                console.log('Completed.', message);
            }
        }
    );
};

declare module 'rxjs/internal/Observable' {
    interface Observable<T> {
        debug: (...any) => Observable<T>;
    }
}

if (environment.production) {
    enableProdMode();
}

const bootstrap = () => platformBrowserDynamic().bootstrapModule(AppModule);

if (environment.hmr) {
    if (module['hot']) {
        hmrBootstrap(module, bootstrap);
    }
    else {
        console.error('HMR is not enabled for webpack-dev-server!');
        console.log('Are you using the --hmr flag for ng serve?');
    }
}
else {
    bootstrap().catch(err => console.log(err));
}
