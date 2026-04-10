import { Component, inject, OnInit, signal, WritableSignal } from '@angular/core';
import { DummyService } from '../../services/dummy';
import { ProductApiResponse, Product as ProductI } from '../../models/product';

@Component({
  selector: 'app-product',
  imports: [],
  templateUrl: './product.html',
  styleUrl: './product.css',
})
export class Product implements OnInit {
  private readonly dummyService: DummyService = inject(DummyService);
  products: WritableSignal<ProductI[] | null> = signal<ProductI[] | null>(null);
  ngOnInit() {
    // this.dummyService.fetchProducts().subscribe((response: ProductApiResponse) => {
    //   this.products = response.products
    // })

    this.dummyService.fetchProducts().subscribe({
      next: (response: ProductApiResponse) => this.products.set(response.products),
      error: (error: Error) => console.error(error),
    });
  }
}
