import {Component, OnInit} from '@angular/core';
import {MovieService} from "../shared/movie.service";
import {ActivatedRoute} from "@angular/router";
import {Movie} from "../shared/movie.model";
import {Location} from "@angular/common";

@Component({
  selector: 'app-movies-filter',
  templateUrl: './movies-filter.component.html',
  styleUrls: ['./movies-filter.component.css']
})
export class MoviesFilterComponent implements OnInit {

  movies: Movie[];
  possibleOrientations = ['-', 'x']
  titleOrientation: number = 0;
  descriptionOrientation: number = 0;
  ratingOrientation: number = 0;
  priceOrientation: number = 0;
  ascendingDescending: number = 0;

  constructor(private movieService: MovieService,
              private router: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.movieService.filter(
      this.router.snapshot.paramMap.get("title"),
      this.router.snapshot.paramMap.get("description"),
      this.router.snapshot.paramMap.get("rating"),
      this.router.snapshot.paramMap.get("price"),
      {id: 0, title:"-", description:"-", rating:-1, price:-1}
    ).subscribe(movies => this.movies = movies);
  }

  back() {
    this.location.back();
  }

  sort() {
    var ascendDescend = 0;
    var title = "-";
    var description = "-";
    var rating = 0;
    var price = 0;

    if(this.possibleOrientations[this.titleOrientation] != title){
      title = this.possibleOrientations[this.titleOrientation];
    }

    if(this.possibleOrientations[this.descriptionOrientation] != description){
      description = this.possibleOrientations[this.descriptionOrientation];
    }

    if(this.possibleOrientations[this.ratingOrientation] != '-'){
      rating = 1;
    }

    if(this.possibleOrientations[this.priceOrientation] != '-'){
      price = 1;
    }

    if(this.possibleOrientations[this.ascendingDescending] == '-'){
      ascendDescend = -1
    }else{
      ascendDescend = 1;
    }

    this.movieService.filter(
      this.router.snapshot.paramMap.get("title"),
      this.router.snapshot.paramMap.get("description"),
      this.router.snapshot.paramMap.get("rating"),
      this.router.snapshot.paramMap.get("price"),
      {id: ascendDescend, title: title, description: description, rating: rating, price: price}
    ).subscribe(movies => this.movies = movies);
  }

  changeTitleOrientation() {
    this.titleOrientation = (this.titleOrientation + 1) % 2
    console.log("title orientation:", this.possibleOrientations[this.titleOrientation])
  }

  changeRatingOrientation() {
    this.ratingOrientation = (this.ratingOrientation + 1) % 2
    console.log("rating orientation:", this.possibleOrientations[this.ratingOrientation])
  }

  changePriceOrientation() {
    this.priceOrientation = (this.priceOrientation + 1) % 2
    console.log("price orientation:", this.possibleOrientations[this.priceOrientation])
  }
  changeDescriptionOrientation() {
    this.descriptionOrientation = (this.descriptionOrientation + 1) % 2
    console.log("description orientation:", this.possibleOrientations[this.descriptionOrientation])
  }

  changeAscendingDescending(){
    this.ascendingDescending = (this.ascendingDescending + 1) % 2
    console.log("ascending/descending orientation:", this.possibleOrientations[this.ascendingDescending])
  }
}
