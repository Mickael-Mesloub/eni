import { ChangeDetectionStrategy, Component, signal } from '@angular/core';
import { form, FormField, required, email, submit } from '@angular/forms/signals';
import { UserAccount } from '../../../types/user-account';

@Component({
  selector: 'app-register',
  imports: [FormField],
  templateUrl: './register.html',
  styleUrl: './register.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Register {
  registerModel = signal<UserAccount>({
    firstName: '',
    email: '',
    password: '',
  });

  registerForm = form(this.registerModel, (fieldPath) => {
    required(fieldPath.firstName, { message: 'First name is required' });
    required(fieldPath.email, { message: 'Email is required' });
    email(fieldPath.email, { message: 'Enter a valid email address' });
    required(fieldPath.password, { message: 'Password is required' });
  });

  onSubmit(event: Event) {
    // Prevent page reload
    event.preventDefault();
    submit(this.registerForm, async () => {
      // Get data submitted
      const data = this.registerModel();

      // Get userAccounts from localstorage if existing
      const usersExistInLocalStorage = localStorage.getItem('userAccounts');
      let userAccounts = [];

      // If we have user accounts in localstorage, fill userAccounts array with parsed data we got from ls
      if (usersExistInLocalStorage != null) {
        userAccounts = JSON.parse(usersExistInLocalStorage);
      }

      // Add new account from register form to ls userAccounts
      userAccounts.push(data);
      localStorage.setItem('userAccounts', JSON.stringify(userAccounts));
    });
  }
}
