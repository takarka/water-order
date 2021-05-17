import { ErrorHandler, Injectable } from '@angular/core';
import { UNAUTHORIZED, BAD_REQUEST, FORBIDDEN } from 'http-status-codes';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Injectable({
    providedIn: 'root'
})
export class GlobaleErrorHandler implements ErrorHandler {

    static readonly REFRESH_PAGE_ON_TOAST_CLICK_MESSAGE: string = 'An error occurred: Please click this message to refresh';
    static readonly DEFAULT_ERROR_TITLE: string = 'Something went wrong';

    constructor(private router: Router, private snackBar: MatSnackBar) { }


    public handleError(error: any) {
        const httpErrorCode = error.httpErrorCode;
        const message = error.message ? error.message : error.toString();
        switch (httpErrorCode) {
            case UNAUTHORIZED:
                this.router.navigateByUrl('/login');
                break;
            case FORBIDDEN:
                this.router.navigateByUrl('/unauthorized');
                break;
            case BAD_REQUEST:
                this.showError(error.message);
                break;
            default:
                this.showError(message);
        }
    }

    private showError(message: string) {
        console.log(message);
        this.snackBar.open(message, null, {
            duration: 2000,
          });
/*
        this.toastManager.error(message, DEFAULT_ERROR_TITLE, { dismiss: 'controlled' }).then((toast: Toast) => {
            let currentToastId: number = toast.id;
            this.toastManager.onClickToast().subscribe(clickedToast => {
                if (clickedToast.id === currentToastId) {
                    this.toastManager.dismissToast(toast);
                    window.location.reload();
                }
            });
        });
        */
    }
}
