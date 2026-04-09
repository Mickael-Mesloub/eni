import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const userGuard: CanActivateFn = (route, state) => {
  // Simulation de contrôle d'authentification
  const isAuth: boolean = false;
  const router: Router = inject(Router);

  if (isAuth) {
    // Autoriser l'accès si l'utilisateur est authentifié
    return true;
  } else {
    // Refuser l'accès et la redirection
    router.navigate([''], {
      queryParams: {
        returnUrl: state.url,
        message: 'Vous devez être connecté pour accéder à cette page',
      },
    });
  }

  return false;
};
