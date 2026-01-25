import { Component, inject, OnInit } from '@angular/core';
import {
  FetchChampionsApiResponse,
  ChampionEntity,
  convertChampionDtoToEntity,
} from '../../types/champion';
import { ChampionService } from '../../services/champion';
import { Router } from '@angular/router';

@Component({
  selector: 'app-champions',
  imports: [],
  templateUrl: './champions.html',
  styleUrl: './champions.css',
})
export class Champions implements OnInit {
  private readonly championService = inject(ChampionService);
  private readonly router = inject(Router);
  champions: ChampionEntity[] = [];

  ngOnInit() {
    this.championService.fetchChampions().subscribe({
      next: (response: FetchChampionsApiResponse) => {
        for (const _champion of Object.values(response.data)) {
          const champion: ChampionEntity = convertChampionDtoToEntity(_champion);
          this.champions.push(champion);
        }
      },
      error: (error) => console.error('Failed to fetch champions : ', error),
    });
  }

  navigateToChampionDetails(championId: string) {
    this.router.navigate(['/champions/', championId]);
  }
}
