import {Component, OnInit} from '@angular/core';
import {Rental} from "../shared/rental.model";
import {RentalService} from "../shared/rental.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-rentals-list',
  templateUrl: './rentals-list.component.html',
  styleUrls: ['./rentals-list.component.css']
})
export class RentalsListComponent implements OnInit {
  rentals: Rental[];
  selectedRental: Rental;

  constructor(private rentalService: RentalService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.rentalService.getAll()
      .subscribe(rentals => this.rentals = rentals);
  }

  updateSelected(rental: Rental) {
    this.selectedRental = rental;
  }

  delete() {
    console.log("deleting rental ", this.selectedRental);
    this.rentalService.delete(this.selectedRental.id)
      .subscribe(_ => {
        console.log("done");

        this.rentals = this.rentals
          .filter(rental => rental.id !== this.selectedRental.id);
      })
  }

  update() {
    this.router.navigate(['rentals/update/', this.selectedRental.id]);
  }
}
