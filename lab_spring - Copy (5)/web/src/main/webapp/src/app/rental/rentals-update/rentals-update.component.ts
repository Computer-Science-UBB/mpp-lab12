import {Component, Input, OnInit} from '@angular/core';
import {RentalService} from "../shared/rental.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {Rental} from "../shared/rental.model";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-rentals-update',
  templateUrl: './rentals-update.component.html',
  styleUrls: ['./rentals-update.component.css']
})
export class RentalsUpdateComponent implements OnInit {

  @Input() rental: Rental;

  constructor(private rentalService: RentalService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.rentalService.getRental(+params['id'])))
      .subscribe(rental => this.rental = rental);
  }

  back() {
    this.location.back();
  }

  save(clientID: string, movieID: string) {
    console.log("updating ", this.rental, ' => ', clientID, ' ', movieID);
    if (clientID.length != 0)
      this.rental.clientID = +clientID;
    if (movieID.length != 0)
      this.rental.movieID = +movieID;

    this.rentalService.update(this.rental)
      .subscribe(_ => this.back());
    this.back();
  }
}
