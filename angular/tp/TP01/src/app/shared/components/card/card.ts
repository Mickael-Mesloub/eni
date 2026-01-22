import { Component, input, InputSignal } from '@angular/core';

@Component({
  selector: 'app-card',
  imports: [],
  templateUrl: './card.html',
  styleUrl: './card.css',
})
export class Card {
  title: InputSignal<string> = input('');
  imgUrl: InputSignal<string> = input('');
}
