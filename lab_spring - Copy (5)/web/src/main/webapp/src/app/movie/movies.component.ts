import {Component, OnInit} from '@angular/core';
import {Router, ROUTER_CONFIGURATION} from "@angular/router";
import {MoviesFilterComponent} from "./movies-filter/movies-filter.component";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  add() {
    console.log("add movie button clicked");
    this.router.navigate(['movies/new']);
  }

  filter(title: string, description: string, rating: string, price:string) {
    console.log("filter button clicked: title:", title, " description:", description, " rating:", rating, " price", price);
    this.router.navigate(['movies/filter/:title/:description/:rating/:price', {
      title: title,
      description: description,
      rating: rating,
      price: price
    }]);
  }
}
