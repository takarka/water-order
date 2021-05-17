import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { User } from '../model/user.model';
import { AccountService } from './account.service';
import { UserService } from '../service/user.service';

@Injectable({ providedIn: 'root' })
export class PrincipalService {
    public userIdentity: User;
    private authenticated = false;
    private authenticationState = new Subject<any>();

    constructor(private account: AccountService, private userService: UserService) {} 

    authenticate(identity) {
        this.userIdentity = identity;
        this.authenticated = identity !== null;
        this.authenticationState.next(this.userIdentity);
    }

    hasAnyAuthority(roles: string[]): Promise<boolean> {
        return Promise.resolve(this.hasAnyAuthorityDirect(roles));
    }

    hasAnyAuthorityDirect(roles: string[]): boolean {
        if (!this.authenticated || !this.userIdentity || !this.userIdentity.roles) {
            return false;
        }

        for (let i = 0; i < roles.length; i++) {
            if (this.userIdentity.roles.includes(roles[i])) {
                return true;
            }
        }

        return false;
    }

    hasAuthority(authority: string): Promise<boolean> {
        if (!this.authenticated) {
            return Promise.resolve(false);
        }

        return this.identity().then(
            id => {
                return Promise.resolve(id.roles && id.roles.includes(authority));
            },
            () => {
                return Promise.resolve(false);
            }
        );
    }

    identity(force?: boolean): Promise<any> {
        console.log(this.userIdentity);
        if (force === true) {
            this.userIdentity = undefined;
        }

        // check and see if we have retrieved the userIdentity data from the server.
        // if we have, reuse it by immediately resolving
        if (this.userIdentity) {
            return Promise.resolve(this.userIdentity);
        }

        // retrieve the userIdentity data from the server, update the identity object, and then resolve.
        return this.account
            .get()
            .toPromise()
            .then(response => {
                const account = response.body;
                if (account) {
                    this.userIdentity = account;
                    this.authenticated = true;
                } else {
                    this.userIdentity = undefined;
                    this.authenticated = false;
                }
                if (this.userIdentity) {
                    if (this.userIdentity.imageUrl) {
                        this.userService.getImage(this.userIdentity.username).subscribe(image => {
                            if (this.userIdentity.imageUrl.indexOf('png') > -1) {
                                this.userIdentity.image = 'data:image/png;base64,' + image.data;
                            } else {
                                this.userIdentity.image = 'data:image/jpg;base64,' + image.data;
                            }
                        });
                    } else {
                        this.userIdentity.imageUrl = '../../../assets/images/avatar.png';
                        this.userIdentity.image = '../../../assets/images/avatar.png';
                    }
                } else {
                    this.userIdentity.imageUrl = undefined;
                    this.userIdentity.image = undefined;
                }
                this.authenticationState.next(this.userIdentity);
                console.log('User :', this.userIdentity);
                return this.userIdentity;
            })
            .catch(err => {
                this.userIdentity = undefined;
                this.authenticated = false;
                this.authenticationState.next(this.userIdentity);
                return null;
            });
    }

    isAuthenticated(): boolean {
        return this.authenticated;
    }

    isIdentityResolved(): boolean {
        return this.userIdentity !== undefined;
    }

    getAuthenticationState(): Observable<any> {
        return this.authenticationState.asObservable();
    }

    getImageUrl(): string {
        return this.isIdentityResolved() ? this.userIdentity.imageUrl : null;
    }

    getImage() {
        return this.userIdentity.image;
    }
}
