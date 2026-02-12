import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';

@Component({
  selector: 'app-article',
  imports: [CommonModule],
  templateUrl: './article.html',
  styleUrl: './article.css',
})
export class ArticlesPage {
private httpClient: HttpClient = inject(HttpClient);
articles: any[] = [];

  callApi(): void {
    console.log("Call api");  
    this.httpClient.get("http://localhost:3000/articles").subscribe(
      {
        next: (response: any) => {
          this.articles = response.data;
        }
      }
    )
  }
}
