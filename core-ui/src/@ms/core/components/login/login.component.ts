import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JhiEventManager } from 'ng-jhipster';

import { FuseConfigService } from '@fuse/services/config.service';
import { fuseAnimations } from '@fuse/animations';
import { LoginService } from './login.service';
import { Router, ActivatedRoute } from '@angular/router';
import { StateStorageService } from '../../auth/state-storage.service';
import { AuthService } from '@ms/core/auth/auth.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    animations: fuseAnimations
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    authenticationError: boolean;
    credentials: any;
    returnUrl: any;

    /**
     * Constructor
     *
     * @param {FuseConfigService} _fuseConfigService
     * @param {FormBuilder} _formBuilder
     */
    constructor(
        private _fuseConfigService: FuseConfigService,
        private _formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private stateStorageService: StateStorageService,
        private eventManager: JhiEventManager,
        private authService: AuthService // {4}
    ) {
        // Configure the layout
        this._fuseConfigService.config = {
            layout: {
                navbar: {
                    hidden: true
                },
                toolbar: {
                    hidden: true
                },
                footer: {
                    hidden: true
                },
                sidepanel: {
                    hidden: true
                }
            }
        };
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void {
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || ['/'];

        this.loginForm = this._formBuilder.group({
            username: ['', [Validators.required]],
            password: ['', Validators.required],
            rememberMe: ['']
        });
    }

    onSubmit() {
        console.log(this.loginForm.get('username').value);
        this.login(
            this.loginForm.get('username').value,
            this.loginForm.get('password').value,
            this.loginForm.get('rememberMe').value
        );
    }

    login(username: string, password: string, rememberMe: boolean) {
        console.log(username, password, rememberMe);
        this.authService.login(this.loginForm.value).subscribe(
            () => {
                this.authenticationError = false;
                // this.activeModal.dismiss('login success');
                if (this.router.url === '/register' || /^\/activate\//.test(this.router.url) || /^\/reset\//.test(this.router.url)) {
                    this.router.navigate(['/orders']);
                }

                this.eventManager.broadcast({
                    name: 'authenticationSuccess',
                    content: 'Sending Authentication Success'
                });

                // // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // // since login is succesful, go to stored previousState and clear previousState
                const redirect = this.stateStorageService.getUrl();
                if (redirect) {
                    this.stateStorageService.storeUrl(null);
                    this.router.navigate([redirect]);
                } else {
                    this.stateStorageService.storeUrl(null);
                    this.router.navigate(['']);
                }
            },
            (error) => {
                this.authenticationError = true;
                console.log('error');
                console.log(error);
            }
        );
    }

    loginWithGoogle() {
        this.authService.loginWithGoogle(this.returnUrl);
    }
}
