import { Component, inject, signal, WritableSignal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginBody } from '../../../types/auth/login-body';
import { AuthService } from '../../../services/auth';

interface LoginFormGroup {
    login: FormControl<LoginBody['login']>;
    password: FormControl<LoginBody['password']>;
  }

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private readonly authService: AuthService = inject(AuthService);
  private readonly router: Router = inject(Router);


  loginModel: WritableSignal<LoginBody> = signal<LoginBody>({
    login: '',
    password:''
  });

  loginForm: FormGroup<LoginFormGroup> = new FormGroup({
    login: new FormControl('',  {
      nonNullable: true, 
      validators: [Validators.required, Validators.minLength(2)]
    }),
    password: new FormControl('', {
      nonNullable: true, 
      validators: [Validators.required, Validators.minLength(6)]
    }),
  });

  invalidCredentialsMessage: string | null = null;

  get login() {
    return this.loginForm.get('login')
  }

  get password() {
    return this.loginForm.get('password')
  }

  submit() {
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

  // isFieldValid(): boolean {

  //   return  this.login?.invalid && (this.login?.dirty || this.login?.touched)
  // }
}
