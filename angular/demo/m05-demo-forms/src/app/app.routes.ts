// import { authGuard } from './guards/auth-guard';

// app.routes.ts
import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { NotFound } from './components/not-found/not-found';
import { Contact } from './components/contact/contact';
import { UserFormComponent } from './components/register/register';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: Home },
    { path: 'contact', component: Contact },
    { path: 'register', component: UserFormComponent },
    { path: '**', component: NotFound },
];

// export const routes: Routes = [
//   {
//     path: 'admin',
//     canActivate: [authGuard],
//     data: { role: 'admin' },
//   },
// ];
