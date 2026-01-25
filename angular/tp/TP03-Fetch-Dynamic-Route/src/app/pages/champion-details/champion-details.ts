import { Component, inject, input, InputSignal, OnInit } from '@angular/core';
import {
  ChampionDetailsEntity,
  convertChampionDetailsDtoToEntity,
  FetchChampionByNameApiResponse,
} from '../../types/champion';
import { ChampionService } from '../../services/champion';

@Component({
  selector: 'app-champion-details',
  imports: [],
  templateUrl: './champion-details.html',
  styleUrl: './champion-details.css',
})
export class ChampionDetails implements OnInit {
  readonly championId: InputSignal<string> = input.required<string>();
  private readonly championService = inject(ChampionService);
  championDetails!: ChampionDetailsEntity;

  ngOnInit(): void {
    this.championService.fetchChampionByName(this.championId()).subscribe({
      next: (response: FetchChampionByNameApiResponse) => {
        for (const _champion of Object.values(response.data)) {
          this.championDetails = convertChampionDetailsDtoToEntity(_champion);
        }

        console.log(this.championDetails);
      },
      error: (error) => console.error('Failed to fetch ' + this.championId() + ' data : ', error),
    });
  }
}
