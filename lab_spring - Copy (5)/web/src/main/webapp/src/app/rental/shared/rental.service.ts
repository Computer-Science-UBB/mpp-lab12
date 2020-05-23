import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Rental} from "./rental.model";
import {map} from "rxjs/operators";

@Injectable()
export class RentalService {
  private rentalsURL = 'http://localhost:8080/api/rentals';

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<Rental[]> {
    return this.httpClient
      .get<Array<Rental>>(this.rentalsURL);
  }

  save(rental: Rental): Observable<Rental> {
    console.log("saving rental: ", rental)

    return this.httpClient
      .post<Rental>(this.rentalsURL, rental)
  }

  delete(id: number) {
    const deleteURL = `${this.rentalsURL}/${id}`;
    return this.httpClient.delete(deleteURL);
  }

  getRental(id: number) {
    return this.getAll()
      .pipe(map(rentals => rentals
        .find(rental => rental.id == id)));
  }

  update(rental: Rental) {
    const updateURL = `${this.rentalsURL}/${rental.id}`;
    return this.httpClient
      .put<Rental>(updateURL, rental);
  }

  filter(clientID: string, movieID: string): Observable<Rental[]> {
    if (clientID.length == 0)
      clientID = '-1';
    if (movieID.length == 0)
      movieID = '-1'
    const filterURL = `${this.rentalsURL}/${+clientID}/${+movieID}`
    return this.httpClient
      .get<Array<Rental>>(filterURL);
  }
}
