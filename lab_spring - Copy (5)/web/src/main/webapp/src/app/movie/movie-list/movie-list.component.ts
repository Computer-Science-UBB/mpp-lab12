import {Component, OnInit} from '@angular/core';
import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-movies-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MoviesListComponent implements OnInit {
  movies: Movie[];
  selectedMovie: Movie;
  pageNo: number = 0;
  pages: number[];

  constructor(private movieService: MovieService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getPaged();
  }

  getPaged(){
    return this.movieService.getPage(this.pageNo).subscribe(
      data => {
        this.movies = data['content'];
        this.pages = new Array(data['totalPages']+1);
        console.log(this.pages);
        console.log(data)
      },
      (error) => {
        console.log(error.error.message);
      }
    );
  }

  updateSelected(movie: Movie) {
    console.log("selected: ", movie);
    this.selectedMovie = movie;
  }

  delete() {
    console.log("deleting movie", this.selectedMovie);
    this.movieService.delete(this.selectedMovie.id)
      .subscribe(_ => {
        console.log("done");

        this.movies = this.movies
          .filter(movie => movie.id !== this.selectedMovie.id);
      })
  }

  update() {
    this.router.navigate(['movies/update/', this.selectedMovie.id]);
  }

  setPage(i: number, $event: MouseEvent) {
    event.preventDefault();
    this.pageNo = i;
    this.getPaged();
  }
}
