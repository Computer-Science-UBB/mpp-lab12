import {Component, OnInit} from '@angular/core';
import {MovieService} from "../shared/movie.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-movies-new',
  templateUrl: './movies-new.component.html',
  styleUrls: ['./movies-new.component.css']
})
export class MoviesNewComponent implements OnInit {

  constructor(private movieService: MovieService,
              private location: Location) {
  }

  ngOnInit(): void {
  }

  save(title: string, description: string, rating: string, price:string) {
    console.log("save movie ", title, ' ', description, ' ', rating,' ',price);
    title = title + ' '
    description = description + ' '
    this.movieService.save({id: 0, title: title, description: description, rating: +rating, price: +price})
      .subscribe(movie => {
        console.log("saved movie: ", movie);
        this.back();
      });
  }

  back() {
    this.location.back();
  }
}
