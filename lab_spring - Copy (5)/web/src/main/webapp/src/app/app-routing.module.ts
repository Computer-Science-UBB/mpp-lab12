import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClientsComponent} from "./client/clients.component";
import {ClientsNewComponent} from "./client/clients-new/clients-new.component";
import {ClientsUpdateComponent} from "./client/clients-update/clients-update.component";
import {ClientsFilterComponent} from "./client/clients-filter/clients-filter.component";
import {MoviesComponent} from "./movie/movies.component";
import {MoviesNewComponent} from "./movie/movies-new/movies-new.component";
import {MoviesUpdateComponent} from "./movie/movies-update/movies-update.component";
import {MoviesFilterComponent} from "./movie/movies-filter/movies-filter.component";
import {RentalsComponent} from "./rental/rentals.component";
import {RentalsUpdateComponent} from "./rental/rentals-update/rentals-update.component";
import {RentalsFilterComponent} from "./rental/rentals-filter/rentals-filter.component";
import {RentalsNewComponent} from "./rental/rentals-new/rentals-new.component";

const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },

  {path: 'clients', component: ClientsComponent},
  {path: 'clients/new', component: ClientsNewComponent},
  {path: 'clients/update/:id', component: ClientsUpdateComponent},
  {path: 'clients/filter/:firstname/:secondname/:job/:age', component: ClientsFilterComponent},
  {path: 'movies', component: MoviesComponent},
  {path: 'movies/new', component: MoviesNewComponent},
  {path: 'movies/update/:id', component: MoviesUpdateComponent},
  {path: 'movies/filter/:title/:description/:rating/:price', component: MoviesFilterComponent},
  {path: 'rentals', component: RentalsComponent},
  {path: 'rentals/new', component: RentalsNewComponent},
  {path: 'rentals/update/:id', component: RentalsUpdateComponent},
  {path: 'rentals/filter/:clientID/:movieID', component: RentalsFilterComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
