import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth-service';

export const userGuard: CanActivateFn = (route, state) => {
  const authService: AuthService = inject(AuthService);
  const router: Router = inject(Router);

  const currentUser = authService.currentUser();

  if (currentUser) {
    // Autoriser l'accès si l'utilisateur est authentifié
    return true;
  }

  // Refuser l'accès et la redirection
  router.navigate(['/login']);

  return false;
};
