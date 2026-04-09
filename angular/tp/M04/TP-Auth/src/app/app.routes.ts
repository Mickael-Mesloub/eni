import { Routes } from '@angular/router';
import { userGuard } from './guards/user-guard';

export const routes: Routes = [
    {path: '', loadComponent: () => import('./pages/home/home').then(c => c.Home) },
    {path: 'auth', loadChildren: () => import('./pages/auth/auth.routes').then(c => c.AUTH_ROUTES)},

    // TODO: route /profil protégée 
    {path: 'profile', canActivateChild: [userGuard], loadComponent: () => import('./pages/profile/profile').then(c => c.Profile) },

    //{path: 'admin', canActivateChild: [userGuard], loadChildren: () => import('./pages/admin/admin.routes').then(c => c.ADMIN_ROUTES)},
    {path: '**', redirectTo: '', pathMatch: 'full'}
];
