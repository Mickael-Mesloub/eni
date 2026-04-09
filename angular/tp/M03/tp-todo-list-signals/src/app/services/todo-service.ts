import { computed, Injectable, Signal, signal, WritableSignal } from '@angular/core';
import { Todo } from '../types/todo';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private readonly todos: WritableSignal<Todo[] | []> = signal<Todo[] | []>([
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
  ]);

  completedTodos: Signal<Todo[] | []> = computed(() => this.todos().filter((todo) => todo.isDone));
  incompleteTodos: Signal<Todo[] | []> = computed(() =>
    this.todos().filter((todo) => !todo.isDone),
  );

  private editingId: number | null = null;

  // -------- METHODS --------

  getTodos(): Todo[] {
    return this.todos();
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

  updateTodo(id: number, newTitle: string) {
    if (newTitle.trim()) {
      this.todos.update((todos) =>
        todos.map((todo) => (todo.id === id ? { ...todo, title: newTitle.trim() } : todo)),
      );
    }

    this.editingId = null;
  }

   startEdit(id: number) {
    this.editingId = id;
  }
}
