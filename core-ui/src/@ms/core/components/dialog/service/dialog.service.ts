import { MatDialogConfig, MatDialog, MatDialogRef, MatSnackBar } from '@angular/material';
import { Injectable, EventEmitter, ComponentFactoryResolver, Injector, ApplicationRef } from '@angular/core';
import { ComponentType } from '@angular/cdk/portal';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

export interface IDialogConfig extends MatDialogConfig {
  title?: string;
  message: string;
}

export interface IAlertConfig extends IDialogConfig {
  closeButton?: string;
}

export interface IConfirmConfig extends IDialogConfig {
  acceptButton?: string;
  cancelButton?: string;
}

export interface IPromptConfig extends IConfirmConfig {
  value?: string;
}

@Injectable({
  providedIn: 'root',
})
export class DialogService {
  constructor(
    private _dialog: MatDialog,
    public _snackBar: MatSnackBar,
    private _breakpointObserver: BreakpointObserver,
    private _injector: Injector,
    private componentFactoryResolver: ComponentFactoryResolver,
    private appRef: ApplicationRef
  ) {}

  /**
   * params:
   * - component: ComponentType<T>
   * - config: MatDialogConfig
   * Wrapper function over the open() method in MatDialog.
   * Opens a modal dialog containing the given component.
   */
  public open<T>(
    component: ComponentType<T>,
    closeEvent: EventEmitter<any>,
    config?: MatDialogConfig
  ): MatDialogRef<T> {
    /*
      const componentFactory = this._componentFactoryResolver.resolveComponentFactory(
            component
        );
        const componentRef = this.viewContainer.createComponent(componentFactory);
        */
    const dialogRef = this._dialog.open(
      component,
      config
        ? config
        : {
            width: '700px',
            maxWidth: 'none',
            autoFocus: true,
            disableClose: false,
          }
    );
    console.log('Dialog Ref:', dialogRef);
    const smallDialogSubscription = this._breakpointObserver.observe(Breakpoints.Handset).subscribe(result => {
      if (result.matches) {
        dialogRef.updateSize('100%', '100%');
      } else {
        if (config) {
          dialogRef.updateSize(config.width, config.height);
        } else {
          dialogRef.updateSize('700px');
        }
      }
    });
    dialogRef.afterClosed().subscribe(val => {
      if (closeEvent) {
        closeEvent.emit(val);
      }
      smallDialogSubscription.unsubscribe();
    });
    return dialogRef;
  }

  public closeAll(): void {
    this._dialog.closeAll();
  }

  public success(message: string, action?: any) {
    this._snackBar.open(message, action, {
      duration: 2000,
      horizontalPosition: 'center',
      verticalPosition: 'top',
      panelClass: 'success',
    });
  }

  public warning(message: string, action?: any) {
    this._snackBar.open(message, action, {
      duration: 4000,
      horizontalPosition: 'center',
      verticalPosition: 'top',
      panelClass: 'warning',
    });
  }

  public info(message: string, action?: any) {
    this._snackBar.open(message, action, {
      duration: 5000,
      horizontalPosition: 'center',
      verticalPosition: 'top',
      panelClass: 'info',
    });
  }

  public error(message: string, duration?: any, action?: any) {
    this._snackBar.open(message, action, {
      duration: duration ? duration : 5000,
      horizontalPosition: 'center',
      verticalPosition: 'top',
      panelClass: 'danger',
    });
  }
}
