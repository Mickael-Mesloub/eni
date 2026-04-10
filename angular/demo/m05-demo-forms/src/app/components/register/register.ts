  import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {
  domainValidator,
  eniEmailValidator,
} from '../../shared/validators/eniMail';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-user-form',
  imports: [ReactiveFormsModule, JsonPipe],
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
})
export class UserFormComponent {
  userForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(2)]),
    email: new FormControl('', [Validators.required, Validators.email, eniEmailValidator()]),
    workEmail: new FormControl('', [Validators.required, Validators.email, domainValidator('eni-ecole.fr')]),
  });

  onSubmit(): void {
    if (this.userForm.valid) {
      console.log('Formulaire valide:', this.userForm.value);
    } else {
      console.log('Formulaire invalide');
      this.markFormGroupTouched();
    }
  }

  // Méthode utilitaire pour marquer tous les champs comme touchés
  private markFormGroupTouched(): void {
    Object.keys(this.userForm.controls).forEach((key) => {
      const control = this.userForm.get(key);
      control?.markAsTouched();
    });
  }

  // Méthodes d'aide pour le template
  isFieldInvalid(fieldName: string): boolean {
    const field = this.userForm.get(fieldName);
    return !!(field && field.invalid && field.touched);
  }

  getFieldError(fieldName: string): string | null {
    const field = this.userForm.get(fieldName);

    if (field?.errors && field.touched) {
      if (field.errors['required']) {
        return `${fieldName} est requis`;
      }
      if (field.errors['email']) {
        return "Format d'email invalide";
      }
      if (field.errors['forbiddenEmail']) {
        return `L'email doit se terminer par @eni.fr. Reçu: ${field.errors['forbiddenEmail'].value}`;
      }
      if (field.errors['forbiddenDomain']) {
        const error = field.errors['forbiddenDomain'];
        return `L'email doit se terminer par @${error.domain}. Reçu: ${error.value}`;
      }
      if (field.errors['minlength']) {
        return `Minimum ${field.errors['minlength'].requiredLength} caractères`;
      }
    }

    return null;
  }
}
