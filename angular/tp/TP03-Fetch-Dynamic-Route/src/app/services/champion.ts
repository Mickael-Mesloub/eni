import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { FetchChampionByNameApiResponse, FetchChampionsApiResponse } from '../types/champion';

const { apiBaseUrl, apiKey } = environment;

@Injectable({
  providedIn: 'root',
})
export class ChampionService {
  private readonly http: HttpClient = inject(HttpClient);

  fetchChampions(): Observable<FetchChampionsApiResponse> {
    const FETCH_CHAMPIONS_API_URL = `${apiBaseUrl}.json?api_key=${apiKey}`;

    return this.http.get<FetchChampionsApiResponse>(FETCH_CHAMPIONS_API_URL);
  }

  fetchChampionByName(championId: string): Observable<FetchChampionByNameApiResponse> {
    const FETCH_CHAMPION_BY_NAME_API_URL = `${apiBaseUrl}/${championId}.json?api_key=${apiKey}`;

    return this.http.get<FetchChampionByNameApiResponse>(FETCH_CHAMPION_BY_NAME_API_URL);
  }
}
