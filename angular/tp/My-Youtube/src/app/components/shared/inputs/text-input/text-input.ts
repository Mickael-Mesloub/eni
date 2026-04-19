import { NgClass } from '@angular/common';
import { Component, input } from '@angular/core';
import { FormsModule } from "@angular/forms";

type TextInputType = 'text' | 'email' | 'password';

@Component({
  selector: 'app-text-input',
  imports: [NgClass, FormsModule],
  templateUrl: './text-input.html',
  styleUrl: './text-input.css',
})
export class TextInput {
  label = input.required<string>();
  type = input<TextInputType>('text');
  name = input.required<string>();
  placeholder = input.required<string>();

  isValid = input.required<boolean>();
  isDirtyOrTouched = input.required<boolean>();
  isTouched = input.required<boolean>();
  isPristine = input.required<boolean>();
  isRequired = input<boolean>();
}
