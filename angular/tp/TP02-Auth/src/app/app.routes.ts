import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { NotFound } from './pages/not-found/not-found';
import { Profile } from './pages/profile/profile';
import { authGuard } from './guards/auth-guard';
import { Register } from './pages/auth/register/register';
import { Login } from './pages/auth/login/login';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: Home },
    { path: 'register', component: Register },
    { path: 'login', component: Login },
    { path: 'profile', component: Profile, canActivate: [authGuard] }, 
    { path: '**', component: NotFound }
];
