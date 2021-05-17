import { AbstractControl, ValidatorFn } from '@angular/forms';

export function notBlankValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    return control.value && control.value.trim() ? null : { key: 'Invalid' };
  };
}
