import { Component, computed, effect, signal } from '@angular/core';

interface CartItem {
  name: string;
  price: number;
  quantity: number
}

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
})
export class App {
  cartItems = signal<CartItem[]>([
    {name: "Apple", price: 1.5, quantity: 2},
    {name: "Banana", price: 0.5, quantity: 5},
  ]);

  totalItems = computed(() => this.cartItems().reduce((sum, item) => sum + item.quantity, 0));
  totalPrice = computed(() => this.cartItems().reduce((sum, item) => sum + item.quantity * item.price, 0));

constructor() {
  effect(() => {
    console.log(`Cart updated. Total items: ${this.totalItems()}. Total price: ${this.totalPrice()}`);
  })
}

addItem() {
  const newItem: CartItem = { name: "Orange", price: 2.0, quantity: 1 };
  this.cartItems.update((items: CartItem[]) => [...items, newItem]);
}

}
