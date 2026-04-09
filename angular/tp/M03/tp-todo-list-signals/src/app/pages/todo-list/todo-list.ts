import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TodoService } from '../../services/todo-service';

@Component({
  selector: 'app-todo-list',
  imports: [FormsModule],
  templateUrl: './todo-list.html',
  styleUrl: './todo-list.css',
})
export class TodoList {
  protected readonly todoService: TodoService = inject(TodoService);

  // TODO : constructeur + récupérer les todos depuis le service

  handleAddTodo(title: string) {
    this.todoService.addTodo(title);
  }

  handleUpdateTodo(id: number, newTitle: string) {
    this.todoService.updateTodo(id, newTitle);
  }

  handleRemoveTodo(id: number) {
    this.todoService.removeTodo(id);
  }

  handleToggleTodo(id: number) {
    this.todoService.toggleTodo(id);
  }
}
