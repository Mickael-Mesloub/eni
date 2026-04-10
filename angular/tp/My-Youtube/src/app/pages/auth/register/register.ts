import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  private readonly router: Router = inject(Router);
  userAlreadyExistsMessage: string | null = null;

  username: string = '';
  email: string = '';
  password: string = '';

  submit(): void {
    console.log('Register submitted');
  }

  navigateToLogin(): void {
    this.router.navigate(['/auth/login']);
  }
}
