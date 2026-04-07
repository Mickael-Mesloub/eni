import { Component } from '@angular/core';
import { HelloWorld } from './pages/hello-world/hello-world';
import { Header } from './components/partial/header/header';
import { ImageToggle } from './components/image-toggle/image-toggle';

@Component({
  selector: 'app-root',
  imports: [HelloWorld, Header, ImageToggle],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  isLoggedIn: boolean = true;
}
