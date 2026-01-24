import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { ApiResponse } from '../types/champion';

const {apiBaseUrl, apiKey} = environment;
const API_URL = `${apiBaseUrl}?api_key=${apiKey}`

@Injectable({
  providedIn: 'root',
})
export class ChampionService {
  private readonly http: HttpClient = inject(HttpClient);

  fetchChampions(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(API_URL);
  }
}
