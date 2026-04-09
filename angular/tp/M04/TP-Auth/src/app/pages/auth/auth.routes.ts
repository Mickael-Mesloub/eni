import { Routes } from '@angular/router';

export const AUTH_ROUTES: Routes = [
  { path: 'login', loadComponent: () => import('./login/login').then((c) => c.Login) },
  { path: 'register', loadComponent: () => import('./register/register').then((c) => c.Register) },
];
