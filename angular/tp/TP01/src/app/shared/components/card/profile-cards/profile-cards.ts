import { Component } from '@angular/core';
import { Card } from '../card';
import { Profile } from '../../../../types/profile';

@Component({
  selector: 'app-profile-cards',
  imports: [Card],
  templateUrl: './profile-cards.html',
  styleUrl: './profile-cards.css',
})
export class ProfileCards {
  readonly profiles: Profile[] = [
    {
      id: 1,
      name: 'Jane',
      imgUrl:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cGVvcGxlfGVufDB8fDB8fHww',
    },
    {
      id: 2,
      name: 'Jack',
      imgUrl:
        'https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cGVvcGxlfGVufDB8fDB8fHww',
    },
    {
      id: 3,
      name: 'Jeanj',
      imgUrl:
        'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
    },
  ];
}
