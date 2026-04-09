import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../services/auth-service';

@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  // Récupérer la valeur depuis un service
  private readonly authService: AuthService = inject(AuthService);
  isAuth: boolean = false;

  ngOnInit() {
    this.isAuth = this.authService.isAuth;
  }
}
