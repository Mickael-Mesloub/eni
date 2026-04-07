import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  imports: [FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  protected readonly title: string = "Page d'accueil";
  name: string = "Jeanj"
  readonly imgUrl: string =
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimages5.alphacoders.com%2F379%2Fthumb-1920-379676.jpg&f=1&nofb=1&ipt=fe5641648ce9a0ff5d08e49f9e2fd8f67e4fc82ca05fc280c553f5baa174d4c4';
  count: number = 0;
  addMore(): void {
    this.count++;
  }
}
