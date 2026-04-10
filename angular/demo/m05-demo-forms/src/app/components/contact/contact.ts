import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contact',
  imports: [FormsModule],
  templateUrl: './contact.html',
  styleUrl: './contact.css',
})
export class Contact {
  contact = { nom: '', email: '' };
  onSubmit(form: any) {
    if (form.valid) {
      console.log('Formulaire valide !', this.contact);
    }
  }
}
