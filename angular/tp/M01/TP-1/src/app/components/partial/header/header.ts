import { Component, input, InputSignal } from '@angular/core';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  isLoggedIn: InputSignal<boolean> = input<boolean>(true);
}
