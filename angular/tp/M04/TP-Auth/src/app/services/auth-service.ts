import { computed, Injectable, signal } from '@angular/core';
import { User, UserInStorage } from '../types/user';
import { RegisterBody } from '../types/auth/register-body';
import { LoginBody } from '../types/auth/login-body';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  users: User[] = [];
  private readonly currentUserSignal = signal<User | null>(null);
  public isAuthenticated = computed(() => this.currentUserSignal() !== null);
  public currentUser = computed(() => this.currentUserSignal());

  constructor() {
    const storedUser = sessionStorage.getItem('currentUser');

    if (storedUser) {
      const user = JSON.parse(storedUser);
      this.currentUserSignal.set(user);
    }
    const usersFromLocalStorage = localStorage.getItem('users');
    this.users = usersFromLocalStorage ? JSON.parse(usersFromLocalStorage) : [];
    console.log([...this.users]);
  }

  login(body: LoginBody): boolean {
    if (!this.validateCredentials(body.login, body.password)) {
      console.log(body.login, ' / ', body.password);
      return false;
    }

    const user: UserInStorage = {
      username: body.login.trim(),
      token: 'jwt-token',
    };

    localStorage.setItem('currentUser', JSON.stringify(user));
    console.log('Connexion réussie !');

    return true;
  }

  register(body: RegisterBody): boolean {
    // Create new user
    const newUser: User = {
      username: body.username.trim(),
      email: body.email.trim(),
      password: body.password.trim(),
    };

    // Check if user already exists in array in localStorage
    const userExists: boolean = this.users.some(
      (user) =>
        user.email.trim() === body.email.trim() || user.username.trim() === body.username.trim(),
    );

    // If user exists, return nothing to stop execution
    if (userExists) return false;

    // Add user in array of users
    this.users.push(newUser);

    // Add array in localStorage
    localStorage.setItem('users', JSON.stringify(this.users));
    console.log('Inscription réussie !');

    return true;
  }

  validateCredentials(login: string, password: string): boolean {
    return this.users.some((user) => user.username === login && user.password === password);
  }
}
