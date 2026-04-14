import { Component, inject } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginBody } from '../../../types/auth/login-body';
import { AuthService } from '../../../services/auth';
import { NgClass } from '@angular/common';

interface LoginFormGroup {
    login: FormControl<LoginBody['login']>;
    password: FormControl<LoginBody['password']>;
  }

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, NgClass],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private readonly authService: AuthService = inject(AuthService);
  private readonly router: Router = inject(Router);
  private readonly fb: FormBuilder = inject(FormBuilder);

  protected readonly loginForm = this.fb.group({
    login: ["", [Validators.required, Validators.minLength(2)]],
    password: ["", [Validators.required, Validators.minLength(8)]]
  })


/*   protected loginForm: FormGroup<LoginFormGroup> = new FormGroup({
    login: new FormControl('',  {
      nonNullable: true, 
      validators: [Validators.required, Validators.minLength(2)]
    }),
    password: new FormControl('', {
      nonNullable: true, 
      validators: [Validators.required, Validators.minLength(6)]
    }),
  }); */

  invalidCredentialsMessage: string | null = null;

  get login() {
    return this.loginForm.get('login')
  }

  get password() {
    return this.loginForm.get('password')
  }

  onSubmit() {
    const formData = this.loginForm.value;

    console.log('BODY : ', formData);

    const isLoggedIn = this.authService.login(formData as LoginBody);

    if (!isLoggedIn) {
      this.invalidCredentialsMessage = 'Identifiants incorrects';

      return;
    }

    this.router.navigate(['/']);
  }

  navigateToRegister(): void {
    this.router.navigate(['/auth/register']);
  }

  // TODO: extract 
  isFieldDirtyOrTouched(field: AbstractControl | null) : boolean {
    return (field?.dirty || field?.touched) ?? false;
  }
}
