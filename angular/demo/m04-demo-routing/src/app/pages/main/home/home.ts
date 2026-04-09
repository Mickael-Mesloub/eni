import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit {
  notif!: string;
  private readonly params = inject(ActivatedRoute);

  ngOnInit() {
    this.notif = this.params.snapshot.queryParams['message'] ?? null;
  }
}
