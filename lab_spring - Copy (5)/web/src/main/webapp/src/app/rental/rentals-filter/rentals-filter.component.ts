import {Component, OnInit} from '@angular/core';
import {Rental} from "../shared/rental.model";
import {RentalService} from "../shared/rental.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-rentals-filter',
  templateUrl: './rentals-filter.component.html',
  styleUrls: ['./rentals-filter.component.css']
})
export class RentalsFilterComponent implements OnInit {
  rentals: Rental[];

  constructor(private rentalService: RentalService,
              private router: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.rentalService.filter(
      this.router.snapshot.paramMap.get('clientID'),
      this.router.snapshot.paramMap.get('movieID')
    ).subscribe(rentals => this.rentals = rentals)
  }

  back() {
    this.location.back();
  }

}
