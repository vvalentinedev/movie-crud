import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from '../models/movie.model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/movies';
  
  private readonly _searchQuery = signal<string>('');
  readonly searchQuery = this._searchQuery.asReadonly();

  getMovies(query?: string): Observable<Movie[]> {
    if (query !== undefined && query.trim() !== '') {
      const params = new HttpParams().set('title', query);
      return this.http.get<Movie[]>(`${this.apiUrl}/search`, { params });
    }
    return this.http.get<Movie[]>(this.apiUrl);
  }

  setSearchQuery(query: string): void {
    this._searchQuery.set(query);
  }
}
