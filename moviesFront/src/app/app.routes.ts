import { Routes } from '@angular/router';
import { MovieListComponent } from './features/movie-list/movie-list';

export const routes: Routes = [
  { path: '', component: MovieListComponent },
  { path: '**', redirectTo: '' }
];
