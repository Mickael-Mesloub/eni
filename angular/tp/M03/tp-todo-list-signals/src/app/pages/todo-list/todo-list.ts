import { Component, computed, inject, OnInit, Signal, signal, WritableSignal } from '@angular/core';
import { Todo } from '../../types/todo';
import { FormsModule } from '@angular/forms';
import { TodoService } from '../../services/todo-service';

@Component({
  selector: 'app-todo-list',
  imports: [FormsModule],
  templateUrl: './todo-list.html',
  styleUrl: './todo-list.css',
})
export class TodoList implements OnInit {
  private readonly todoService: TodoService = inject(TodoService);

  todos: WritableSignal<Todo[] | []> = signal<Todo[] | []>([]);
  editingId: number | null = null;

  completedTodos: Signal<Todo[] | []> = computed(() => this.todos().filter((todo) => todo.isDone));
  incompleteTodos: Signal<Todo[] | []> = computed(() =>
    this.todos().filter((todo) => !todo.isDone),
  );

  ngOnInit() {
    // Initialiser les listes filtrées
    this.todos.set(this.todoService.getTodos());
  }

  addTodo(title: string): void {
    if (!title.trim()) return;

    const newTodo: Todo = {
      id: Date.now(),
      title: title.trim(),
      isDone: false,
      createdAt: new Date(),
    };

    this.todos.update((todos) => [...todos, newTodo]);
  }

  removeTodo(id: number): void {
    this.todos.update((todos) => todos.filter((todo) => todo.id !== id));
  }

  toggleTodo(id: number): void {
    this.todos.update((todos) =>
      todos.map((todo) => (todo.id === id ? { ...todo, isDone: !todo.isDone } : todo)),
    );
  }

  startEdit(id: number) {
    this.editingId = id;
  }

  updateTodo(id: number, newTitle: string) {
    if (newTitle.trim()) {
      this.todos.update((todos) =>
        todos.map((todo) => (todo.id === id ? { ...todo, title: newTitle.trim() } : todo)),
      );
    }
    this.editingId = null;
  }
}
