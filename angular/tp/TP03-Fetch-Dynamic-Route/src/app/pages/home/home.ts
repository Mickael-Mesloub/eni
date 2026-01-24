import { Component, inject, OnInit } from '@angular/core';
import { ChampionService } from '../../services/champion';
import { ApiResponse, ChampionEntity, convertChampionDtoToEntity,  } from '../../types/champion';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit {
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
