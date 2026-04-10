import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from "@angular/router";

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private readonly router: Router = inject(Router);
  login: string = '';
  password: string = '';

  invalidCredentialsMessage: string | null = null;

  submit() {
    console.log('Login submitted');
  }

  navigateToRegister(): void {
    this.router.navigate(['/auth/register'])
  }
}
