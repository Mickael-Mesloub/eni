import { Component, inject, OnInit } from '@angular/core';
import { ApiResponse, ChampionEntity, convertChampionDtoToEntity } from '../../types/champion';
import { ChampionService } from '../../services/champion';

@Component({
  selector: 'app-champions',
  imports: [],
  templateUrl: './champions.html',
  styleUrl: './champions.css',
})
export class Champions implements OnInit {
  champions: ChampionEntity[] = []; 
  private readonly championService = inject(ChampionService);

  ngOnInit() {
    this.championService.fetchChampions().subscribe({
      next: ( response: ApiResponse ) => {
        for (const _champion of Object.values(response.data)) {
          const champion: ChampionEntity = convertChampionDtoToEntity(_champion);
          this.champions.push(champion);    
        }
      },
      error: (error) => console.error('Failed to fetch champions : ', error),
    });
  }
}
