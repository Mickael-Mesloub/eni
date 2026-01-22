import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Auth } from '../services/auth';

@Component({
  selector: 'app-header',
  imports: [RouterLink],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  isAuthenticated: boolean = inject(Auth).isAuthenticated();
}
