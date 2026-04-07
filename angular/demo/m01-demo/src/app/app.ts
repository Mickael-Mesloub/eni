import { Component, signal } from '@angular/core';
import { Header } from "./partials/header/header";

@Component({
  selector: 'app-root',
  imports: [ Header],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('m01-demo');
}
