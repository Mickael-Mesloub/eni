import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../../../types/user';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register implements OnInit {
  username: string = '';
  email: string = '';
  password: string = '';

  users: User[] = [];

  ngOnInit() {
    const usersFromLocalStorage = localStorage.getItem('users');
    this.users = usersFromLocalStorage ? JSON.parse(usersFromLocalStorage) : [];
    console.log([...this.users]);
  }

  submit(): void {
    const newUser: User = {
      username: this.username,
      email: this.email,
      password: this.password,
    };

    const userExists: boolean = this.users.some((user) => user.email === this.email || user.username === this.username);
    
    if(userExists) return;

    this.users.push(newUser);
    localStorage.setItem('users', JSON.stringify(this.users));
    console.log([...this.users]);
  }
}
