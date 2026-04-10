import { Component, signal } from '@angular/core';
import { Product } from "./components/product/product";

@Component({
  selector: 'app-root',
  imports: [Product],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('m06-demo-http');
}
