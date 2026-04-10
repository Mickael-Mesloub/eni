import { AbstractControl, ValidatorFn } from '@angular/forms';

export function eniEmailValidator(): ValidatorFn {
  return (control: AbstractControl<string>): { [key: string]: any } | null => {
    // Vérifier si la valeur existe et n'est pas vide
    if (!control.value) {
      return null;
      // Ne pas valider si vide (laisser 'required' s'en charger)
    }

    const forbidden = !control.value.endsWith('@eni.fr');
    return forbidden ? { forbiddenEmail: { value: control.value } } : null;
  };
}

// Variante plus flexible avec paramètre
export function domainValidator(allowedDomain: string): ValidatorFn {
  return (control: AbstractControl<string>): { [key: string]: any } | null => {
    if (!control.value) {
      return null;
    }

    const forbidden = !control.value.endsWith(`@${allowedDomain}`);
    return forbidden
      ? { forbiddenDomain: { value: control.value, domain: allowedDomain } }
      : null;
  };
}

export function emailEnterpriseValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    // Ne valide pas si vide (laissé à required)
    if (!control.value) {
      return null;
    }
    const forbidden = !control.value.endsWith('@entreprise.com');

    return forbidden ? { emailEntreprise: { value: control.value } } : null;
  };
}

// validators/age-range.validator.ts
export function ageRangeValidator(min: number, max: number): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    if (!control.value) return null;
    const age = parseInt(control.value);
    const valid = age >= min && age <= max;
    return valid ? null : { ageRange: { min, max, actual: age } };
  };
}

// validators/password-strength.validator.ts
export function passwordStrengthValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    if (!control.value) return null;
    const hasNumber = /[0-9]/.test(control.value);
    const hasUpper = /[A-Z]/.test(control.value);
    const hasLower = /[a-z]/.test(control.value);
    const hasSpecial = /[#?!@$%^&*-]/.test(control.value);
    const hasMinLength = control.value.length >= 8;
    const valid =
      hasNumber && hasUpper && hasLower && hasSpecial && hasMinLength;
    if (valid) return null;
    return {
      passwordStrength: {
        hasNumber,
        hasUpper,
        hasLower,
        hasSpecial,
        hasMinLength,
      },
    };
  };
}
