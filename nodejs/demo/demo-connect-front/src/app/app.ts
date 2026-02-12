import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ArticlesPage } from "./pages/article/article";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ArticlesPage],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('demo-connect-front');
}
