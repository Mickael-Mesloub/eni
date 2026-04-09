import { Injectable, OnInit } from '@angular/core';
import { User } from '../types/user';
import { RegisterBody } from '../types/auth/register-body';
import { LoginBody } from '../types/auth/login-body';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Attributs
  isAuth!: boolean;
  users: User[] = [];

  // Fonction login
  // Récupérer un User (avec username et password)
  // Vérifier si je le trouve en localStorage
  // Vérifier si les informations correspondent
  // Si oui => isAuth passe à true + redirection vers /profile
  // Si non => afficher un message d'erreur (redirection ?)

  // Fonction register
  // Je dois créer un array d'users
  //  utilisateur en localStorage avec :
  // username: Minimum 2 caractères
  // email
  // password: Minimum 6 caractères
  // PENSER A TRIM

  constructor() {
    const usersFromLocalStorage = localStorage.getItem('users');
    this.users = usersFromLocalStorage ? JSON.parse(usersFromLocalStorage) : [];
    console.log([...this.users]);
  }

  // TODO: check if user exists with email AND username + check if password is correct
  login(body: LoginBody): boolean {
    const userExists: boolean = this.users.some(
      (user) =>
        user.email.trim() === body.login.trim() && user.username.trim() === body.login.trim(),
    );

    const isPasswordCorrect: boolean = this.users.some(
      (user) =>
        user.email.trim() === body.login.trim() && user.password.trim() === body.password.trim(),
    );

    if (isPasswordCorrect && userExists) {
      this.isAuth = true;

      return true;
    }

    this.isAuth = false;

    return false;
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
    // TODO: Send error message ?
    if (userExists) return false;

    // Add user in array of users
    this.users.push(newUser);

    // Add array in localStorage
    localStorage.setItem('users', JSON.stringify(this.users));
    console.log([...this.users]);

    return true;
  }
}
