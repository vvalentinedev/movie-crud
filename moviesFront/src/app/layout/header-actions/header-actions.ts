import { Component, inject, OnDestroy } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIcon } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MovieService } from '../../services/movie.service';
import { debounceTime, Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-header-actions',
  imports: [MatFormFieldModule, MatInputModule, MatIcon, FormsModule],
  template: `
    <div class="flex items-center">
      <mat-form-field appearance="outline" class="search-bar no-subscript-wrapper">
        <mat-icon matPrefix class="mr-2">search</mat-icon>
        <input matInput placeholder="Search movies..." [(ngModel)]="searchQuery" (ngModelChange)="onSearchChange($event)">
      </mat-form-field>
    </div>
  `,
  styles: [`
    .search-bar {
      width: 300px;
      --mdc-outlined-text-field-container-color: rgba(255, 255, 255, 0.15);
      --mdc-outlined-text-field-outline-color: rgba(255, 255, 255, 0.3);
      --mdc-outlined-text-field-hover-outline-color: rgba(255, 255, 255, 0.5);
      --mdc-outlined-text-field-focus-outline-color: rgba(255, 255, 255, 1);
      --mdc-outlined-text-field-label-text-color: rgba(255, 255, 255, 0.7);
      --mdc-outlined-text-field-focus-label-text-color: #fff;
      --mdc-outlined-text-field-input-text-color: #fff;
      --mdc-outlined-text-field-caret-color: #fff;
    }

    /* Custom styles to remove the bottom margin from mat-form-field in the toolbar */
    :host ::ng-deep .no-subscript-wrapper .mat-mdc-form-field-subscript-wrapper {
      display: none;
    }
    
    :host ::ng-deep .mat-mdc-text-field-wrapper {
      height: 40px !important;
      display: flex;
      align-items: center;
    }

    :host ::ng-deep .mat-mdc-form-field-flex {
      height: 40px !important;
      align-items: center !important;
    }

    :host ::ng-deep .mat-mdc-form-field-infix {
      padding-top: 0 !important;
      padding-bottom: 0 !important;
      min-height: 40px !important;
      display: flex;
      align-items: center;
    }
    
    mat-icon {
      color: rgba(255, 255, 255, 0.78);
    }
  `],
})
export class HeaderActions implements OnDestroy {
  private readonly movieService = inject(MovieService);
  private readonly searchSubject = new Subject<string>();
  private readonly destroy$ = new Subject<void>();
  
  searchQuery = '';

  constructor() {
    this.searchSubject.pipe(
      debounceTime(300),
      takeUntil(this.destroy$)
    ).subscribe(query => {
      this.movieService.setSearchQuery(query);
    });
  }

  onSearchChange(query: string): void {
    this.searchSubject.next(query);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
