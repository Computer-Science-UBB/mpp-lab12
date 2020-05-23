import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Movie} from "./movie.model";
import {map} from "rxjs/operators";

@Injectable()
export class MovieService {
  private moviesURL = 'http://localhost:8080/api/movies';

  constructor(private httpClient: HttpClient) {
  }

  getALl(): Observable<Movie[]> {
    return this.httpClient
      .get<Array<Movie>>(this.moviesURL);
  }

  delete(id: number) {
    const deleteURL = `${this.moviesURL}/${id}`;
    return this.httpClient.delete(deleteURL);
  }


  getMovie(id: number) {
    return this.getALl()
      .pipe(map(movies => movies
        .find(movie => movie.id == id)));
  }

  update(movie: Movie) {
    const updateURL = `${this.moviesURL}/${movie.id}`;
    return this.httpClient
      .put<Movie>(updateURL, movie);
  }

  save(movie: Movie): Observable<Movie> {
    console.log("saving movie: ", movie);

    return this.httpClient
      .post<Movie>(this.moviesURL, movie);
  }

  filter(title: string, description: string, rating: string,price: string, sortMovie: Movie): Observable<Movie[]> {
    if (rating.length == 0)
      rating = '-1';
    if (price.length == 0)
      price = '-1';
    if (title.length == 0)
      title = title + ' '
    if (description.length == 0)
      description = description + ' '
    const filterURL = `${this.moviesURL}/${title}/${description}/${+rating}/${+price}`
    return this.httpClient
      .post<Array<Movie>>(filterURL, sortMovie);
  }

  getPage(page: number) {
    const pageURL = `${this.moviesURL}/page?page=${page}`
    return this.httpClient.get(pageURL);
  }
}
