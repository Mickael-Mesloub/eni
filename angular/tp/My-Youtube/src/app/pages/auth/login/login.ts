import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from "@angular/router";
import { LoginBody } from '../../../types/auth/login-body';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private readonly authService: AuthService = inject(AuthService);
  private readonly router: Router = inject(Router);
  
  login: string = '';
  password: string = '';

  invalidCredentialsMessage: string | null = null;

  submit() {
    const body: LoginBody = {
      login: this.login,
      password: this.password 
    };

    console.log("BODY : ", body);
    

   const isLoggedIn = this.authService.login(body);

   if(!isLoggedIn) {
      this.invalidCredentialsMessage = "Identifiants incorrects";

      return;
   }

   this.router.navigate(['/']);
  }

  navigateToRegister(): void {
    this.router.navigate(['/auth/register'])
  }
}
