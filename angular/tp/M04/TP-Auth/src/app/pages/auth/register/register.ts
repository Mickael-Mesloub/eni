import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth-service';
import { RegisterBody } from '../../../types/auth/register-body';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  // Inject service
  private readonly authService: AuthService = inject(AuthService);
  private readonly router: Router = inject(Router);

  userAlreadyExistsMessage: string | null = null;

  username: string = '';
  email: string = '';
  password: string = '';

  submit(): void {
    const body: RegisterBody = {
      username: this.username,
      email: this.email,
      password: this.password,
    };

    const isRegistered = this.authService.register(body);

    if (!isRegistered) {
      this.userAlreadyExistsMessage = 'Cet utilisateur existe déjà';

      return;
    }

    // Redirect to /login
    // TODO: Fix redirect
    this.router.navigate(['/login']);
  }
}
