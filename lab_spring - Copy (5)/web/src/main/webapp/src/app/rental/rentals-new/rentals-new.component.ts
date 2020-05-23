import {Component, OnInit} from '@angular/core';
import {RentalService} from "../shared/rental.service";
import {Location} from "@angular/common";
import {Rental} from "../shared/rental.model";

@Component({
  selector: 'app-rentals-new',
  templateUrl: './rentals-new.component.html',
  styleUrls: ['./rentals-new.component.css']
})
export class RentalsNewComponent implements OnInit {

  constructor(private rentalService: RentalService,
              private location: Location) {
  }

  ngOnInit(): void {
  }

  save(clientID: string, movieID: string) {
    console.log("save rental ", clientID, ' ', movieID, ' ');

    this.rentalService.save({id: 0, clientID: +clientID, movieID: +movieID})
      .subscribe(rental => {
        console.log("saved rental: ", rental);
        this.back();
      });
  }

  back() {
    this.location.back();
  }

}
