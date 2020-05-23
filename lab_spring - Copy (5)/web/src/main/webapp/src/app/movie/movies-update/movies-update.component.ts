import {Component, Input, OnInit} from '@angular/core';
import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";
import {ActivatedRoute, Params} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {Location} from "@angular/common";

@Component({
  selector: 'app-movies-update',
  templateUrl: './movies-update.component.html',
  styleUrls: ['./movies-update.component.css']
})
export class MoviesUpdateComponent implements OnInit {

  @Input() movie: Movie;

  constructor(private movieService: MovieService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.movieService.getMovie(+params['id'])))
      .subscribe(movie => this.movie = movie);
  }

  back() {
    this.location.back();
  }

  save(title: string, description: string, rating: string, price: string) {
    console.log("updating ", this.movie, " => ", title, ' ', description, ' ', rating, ' ', price);
    if (title.length != 0)
      this.movie.title = title;
    if (description.length != 0)
      this.movie.description = description;
    if (rating.length != 0)
      this.movie.rating = +rating;
    if (price.length != 0)
      this.movie.price = +price;

    this.movieService.update(this.movie)
      .subscribe(_ => this.back());
  }
}
