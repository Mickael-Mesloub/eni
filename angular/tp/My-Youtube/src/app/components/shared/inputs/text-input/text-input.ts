import { KeyValuePipe, NgClass } from '@angular/common';
import { Component, input, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormsModule } from "@angular/forms";

type TextInputType = 'text' | 'email' | 'password';

@Component({
  selector: 'app-text-input',
  imports: [NgClass, FormsModule, KeyValuePipe],
  templateUrl: './text-input.html',
  styleUrl: './text-input.css',
})
export class TextInput implements OnInit {
  id= input<string>();
  name = input.required<string>();
  type = input<TextInputType>('text');
  label = input.required<string>();
  placeholder = input<string>();
  control =  input.required<AbstractControl>();

/*   isValid = input.required<boolean>();
  isDirtyOrTouched = input.required<boolean>();
  isTouched = input.required<boolean>();
  isPristine = input.required<boolean>();
  isRequired = input<boolean>(); */

  errorMessages: Record<string, string> = {
    required: 'Ce champ est requis',
    email: 'Email invalide'
  }

  ngOnInit(): void {
    this.placeholder = this.placeholder ?? this.label;
  }
}
