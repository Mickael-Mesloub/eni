import { Component, OnInit } from '@angular/core';
import { Todo } from '../../types/todo';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-todo-list',
  imports: [FormsModule],
  templateUrl: './todo-list.html',
  styleUrl: './todo-list.css',
})
export class TodoList implements OnInit {
  todos: Todo[] = [
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

  completedTodos: Todo[] = [];
  incompleteTodos: Todo[] = [];

  ngOnInit() {
    // Initialiser les listes filtrées
    this.updateFilteredTodos();
  }

  private updateFilteredTodos() {
    this.completedTodos = this.todos.filter((todo) => todo.isDone);
    this.incompleteTodos = this.todos.filter((todo) => !todo.isDone);
  }

  addTodo(title: string): void {
    if (!title.trim()) return;

    const newTodo: Todo = {
      id: Date.now(),
      title: title.trim(),
      isDone: false,
      createdAt: new Date(),
    };

    this.todos.push(newTodo);
    this.updateFilteredTodos();
  }

  removeTodo(id: number): void {
    this.todos = this.todos.filter((todo) => todo.id !== id);
  }

  markAsDone(id: number): void {
    this.todos = this.todos.map((todo) =>
      todo.id === id ? { ...todo, isDone: !todo.isDone } : todo,
    );
    this.updateFilteredTodos();
  }
}
