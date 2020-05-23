import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {ClientsComponent} from './client/clients.component';
import {ClientsListComponent} from './client/client-list/client-list.component';
import {ClientsNewComponent} from './client/clients-new/clients-new.component';
import {ClientsUpdateComponent} from './client/clients-update/clients-update.component';
import {ClientsFilterComponent} from './client/clients-filter/clients-filter.component';
import {NgxPaginationModule} from "ngx-pagination";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import {ClientService} from "./client/shared/client.service";
import {MoviesComponent} from "./movie/movies.component";
import {MoviesListComponent} from "./movie/movie-list/movie-list.component";
import {MoviesUpdateComponent} from "./movie/movies-update/movies-update.component";
import {MoviesNewComponent} from "./movie/movies-new/movies-new.component";
import {MoviesFilterComponent} from "./movie/movies-filter/movies-filter.component";
import {MovieService} from "./movie/shared/movie.service";
import {RentalsComponent} from "./rental/rentals.component";
import {RentalsListComponent} from "./rental/rentals-list/rentals-list.component";
import {RentalsUpdateComponent} from "./rental/rentals-update/rentals-update.component";
import {RentalsNewComponent} from "./rental/rentals-new/rentals-new.component";
import {RentalsFilterComponent} from "./rental/rentals-filter/rentals-filter.component";
import {RentalService} from "./rental/shared/rental.service";

@NgModule({
  declarations: [
    AppComponent,
    ClientsComponent,
    ClientsListComponent,
    MoviesComponent,
    MoviesListComponent,
    RentalsComponent,
    RentalsListComponent,
    ClientsNewComponent,
    ClientsUpdateComponent,
    MoviesUpdateComponent,
    MoviesNewComponent,
    RentalsNewComponent,
    RentalsUpdateComponent,
    ClientsFilterComponent,
    MoviesFilterComponent,
    RentalsFilterComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgxPaginationModule,
    MatTableModule,
    MatSortModule,
    BrowserAnimationsModule
  ],
  providers: [ClientsComponent, ClientService, MoviesComponent, MovieService,  RentalsComponent, RentalService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
