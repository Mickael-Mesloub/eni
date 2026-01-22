import { Component } from '@angular/core';
import { ProfileCards } from '../../shared/components/card/profile-cards/profile-cards';

@Component({
  selector: 'app-home',
  imports: [ProfileCards],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {}
