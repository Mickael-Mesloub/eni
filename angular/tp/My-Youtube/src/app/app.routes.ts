import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', loadComponent: () => import('./pages/home/home').then((c) => c.Home) },
  { path: 'auth', loadChildren: () => import('./pages/auth/auth.routes').then((c) => c.AUTH_ROUTES) },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];
