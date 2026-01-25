import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { NotFound } from './pages/not-found/not-found';
import { Champions } from './pages/champions/champions';
import { ChampionDetails } from './pages/champion-details/champion-details';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: Home },
  { path: 'champions', component: Champions },
  { path: 'champions/:championId', component: ChampionDetails },
  { path: '**', component: NotFound },
];
