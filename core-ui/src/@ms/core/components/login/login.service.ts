import { Injectable } from '@angular/core';
// import { JhiLanguageService } from 'ng-jhipster';

import { PrincipalService } from '@ms/core/auth/principal.service';
import { AuthServerProvider } from '@ms/core/auth/auth-jwt.service';

@Injectable({ providedIn: 'root' })
export class LoginService {
    constructor(
        // private languageService: JhiLanguageService,
        private principal: PrincipalService,
        private authServerProvider: AuthServerProvider
    ) {}

    login(credentials, callback?) {
        const cb = callback || function() {};

        return new Promise((resolve, reject) => {
            this.authServerProvider.login(credentials).subscribe(
                data => {
                    console.log('login data:', data);
                    this.principal.identity(true).then(account => {
                        // After the login the language will be changed to
                        // the language selected by the user during his registration
                        if (account !== null) {
                            // this.languageService.changeLanguage(account.langKey);
                        }
                        resolve(data);
                    });
                    return cb();
                },
                err => {
                    console.log(err);
                    this.logout();
                    reject(err);
                    return cb(err);
                }
            );
        });
    }

    logout() {
        if (this.principal.isAuthenticated()) {
            this.authServerProvider.logout().subscribe(() => this.principal.authenticate(null));
        } else {
            this.principal.authenticate(null);
        }
    }
}
