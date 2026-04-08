import { Injectable } from '@angular/core';
import { User as UserType } from '../types/user'

@Injectable({
  providedIn: 'root',
})
export class User {
  private readonly users: UserType[] = [
    {id: 1, name: 'Joe'},
    {id: 2, name: 'Pablo'},
    {id: 3, name: 'Marina'},
  ];

  getUsers(): UserType[] | [] {
    return this.users;
  }
}
