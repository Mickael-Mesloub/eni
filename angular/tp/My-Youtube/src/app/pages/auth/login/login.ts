import { Component, inject, signal, WritableSignal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginBody } from '../../../types/auth/login-body';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private readonly authService: AuthService = inject(AuthService);
  private readonly router: Router = inject(Router);

  loginBody: WritableSignal<LoginBody | null> = signal<LoginBody | null>(null);

  loginForm = new FormGroup({
    login: new FormControl('', [Validators.required, Validators.minLength(2)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  });

  invalidCredentialsMessage: string | null = null;

  get login() {
    return this.loginForm.get('login')
  }

  get password() {
    return this.loginForm.get('password')
  }

  submit() {
    const body: LoginBody = {
      login: this.loginBody()?.login ?? '',
      password: this.loginBody()?.password ?? '',
    };

    console.log('BODY : ', body);

    const isLoggedIn = this.authService.login(body);

    if (!isLoggedIn) {
      this.invalidCredentialsMessage = 'Identifiants incorrects';

      return;
    }

    this.router.navigate(['/']);
  }

  navigateToRegister(): void {
    this.router.navigate(['/auth/register']);
  }
}
