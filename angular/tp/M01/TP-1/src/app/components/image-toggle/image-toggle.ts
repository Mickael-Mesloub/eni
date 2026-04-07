import { Component } from '@angular/core';

@Component({
  selector: 'app-image-toggle',
  imports: [],
  templateUrl: './image-toggle.html',
  styleUrl: './image-toggle.css',
})
export class ImageToggle {
  imageVisible: boolean = false;
  clickCount: number = 0;
  readonly imgUrl: string =
    'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimages5.alphacoders.com%2F379%2Fthumb-1920-379676.jpg&f=1&nofb=1&ipt=fe5641648ce9a0ff5d08e49f9e2fd8f67e4fc82ca05fc280c553f5baa174d4c4';

  toggle(): void {
    this.clickCount++;
    this.imageVisible = !this.imageVisible;
  }
}
