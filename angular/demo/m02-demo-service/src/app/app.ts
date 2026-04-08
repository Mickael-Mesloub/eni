import { Component, inject, OnInit } from '@angular/core';
import { User as UserService } from './services/user';
import { User } from './types/user';
import { TitleCasePipe } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [TitleCasePipe],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit{
  // Inject service
  // constructor(private readonly userService: UserService){}
  private readonly userService: UserService = inject(UserService);

  users?: User[];

  ngOnInit() {
    this.users = this.userService.getUsers();
  }

  // Récupérer directement les utilisateurs 
  // users?: User = inject(UserService).getUsers();
}
