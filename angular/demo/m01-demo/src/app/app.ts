import { Component, signal } from '@angular/core';
import { Header } from "./partials/header/header";
import { Home } from "./pages/home/home";

@Component({
  selector: 'app-root',
  imports: [Header, Home],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('m01-demo');
}
