import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { ProductApiResponse } from '../models/product';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Injectable({
  providedIn: 'root',
})
export class DummyService {
  private readonly http: HttpClient = inject(HttpClient);
  private readonly baseUrl: string = environment.dummyApiUrl;

  // Call API avec typage:
  // l'api va renvoyer une ProductApiResponse
  // et HttpClient renvoie un Observable<ProductApiResponse>
  fetchProducts(): Observable<ProductApiResponse> {
    return this.http.get<ProductApiResponse>(`${this.baseUrl}/products`).pipe(takeUntilDestroyed());
  }
}
