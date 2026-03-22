import { Component } from '@angular/core';
import { MatToolbar } from '@angular/material/toolbar';
import { HeaderActions } from "../header-actions/header-actions";

@Component({
  selector: 'app-header',
  imports: [MatToolbar, HeaderActions],
  template: ` 
    <mat-toolbar class="w-full elevated py-2 bg-[#6b06e6] text-white">
      <div class="max-w-[1200px] mx-auto w-full flex items-center justify-between px-4">
        <span class="text-2xl font-bold tracking-tight">Movies DB</span>
        <app-header-actions />
      </div>
    </mat-toolbar>
  `,
  styles: `
    :host {
      display: block;
    }
    .mat-toolbar {
      background-color: #6b06e6 !important; /* Deep Purple 900 */
      color: white !important;
    }
  `,
})
export class Header {

}
