import { Component, inject } from '@angular/core';
import { AuthService } from '../../../services/auth-service';
import { LoginBody } from '../../../types/auth/login-body';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private readonly authService: AuthService = inject(AuthService);
  private readonly router: Router = inject(Router);

  login: string = '';
  password: string = '';

  invalidCredentialsMessage: string | null = null;

  submit(): void {
    const body: LoginBody = {
      login: this.login,
      password: this.password 
    };

   const isLoggedIn = this.authService.login(body);

   if(!isLoggedIn) {
      this.invalidCredentialsMessage = "Identifiants incorrects";

      return;
   }

    this.router.navigate(['/profile']);
  }
}
