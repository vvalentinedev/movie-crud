import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-movie-list',
  imports: [CommonModule, MatCardModule, MatButtonModule],
  template: `
    <div class="container mx-auto p-4">
      <h1 class="text-3xl font-bold mb-6 text-center">Movie Collection</h1>
      
      @if (loading()) {
        <div class="flex justify-center items-center h-64">
          <p class="text-xl">Loading movies...</p>
        </div>
      } @else if (error()) {
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-6" role="alert">
          <strong class="font-bold">Error!</strong>
          <span class="block sm:inline"> {{ error() }}</span>
        </div>
      } @else {
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          @for (movie of movies(); track movie.id) {
            <mat-card class="flex flex-col h-full hover:shadow-lg transition-shadow duration-300">
              <img mat-card-image 
                   [src]="movie.poster_URL || 'https://via.placeholder.com/300x450?text=No+Poster'" 
                   [alt]="movie.title"
                   class="h-64 object-cover">
              
              <mat-card-header>
                <mat-card-title class="text-lg font-bold">{{ movie.title }}</mat-card-title>
                <mat-card-subtitle>{{ movie.releaseDate }} | {{ movie.duration }} min</mat-card-subtitle>
              </mat-card-header>

              <mat-card-content class="flex-grow mt-2">
                <div class="flex flex-wrap gap-1 mb-2">
                  @for (genre of movie.genres; track genre) {
                    <span class="px-2 py-1 bg-blue-100 text-blue-800 text-xs rounded-full">{{ genre }}</span>
                  }
                </div>
                <p class="text-sm text-gray-600 line-clamp-2">
                  <strong>Director:</strong> {{ movie.directors.join(', ') }}
                </p>
              </mat-card-content>

              <mat-card-actions class="p-4 border-t">
                <button mat-button color="primary">DETAILS</button>
              </mat-card-actions>
            </mat-card>
          }
        </div>

        @if (movies().length === 0) {
          <div class="text-center py-10">
            <p class="text-gray-500 italic">No movies found in the database.</p>
          </div>
        }
      }
    </div>
  `,
  styles: [`
    :host {
      display: block;
      background-color: #f9fafb;
      min-height: calc(100vh - 64px);
    }
  `]
})
export class MovieListComponent implements OnInit {
  private readonly movieService = inject(MovieService);
  
  movies = signal<Movie[]>([]);
  loading = signal(true);
  error = signal<string | null>(null);

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies(): void {
    this.loading.set(true);
    this.movieService.getMovies().subscribe({
      next: (data) => {
        this.movies.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        console.error('Error fetching movies:', err);
        this.error.set('Could not load movies. Please make sure the backend is running.');
        this.loading.set(false);
      }
    });
  }
}
