import { Injectable } from '@angular/core';
import { Todo } from '../types/todo';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private readonly todos: Todo[] = [
      {
        id: Date.now() + 1,
        title: 'Lire un bouquin',
        isDone: false,
        createdAt: new Date(),
      },
      {
        id: Date.now() + 2,
        title: 'Finir le TP',
        isDone: false,
        createdAt: new Date(),
      },
    ];
  
    getTodos(): Todo[] {
      return this.todos;
    }
}
