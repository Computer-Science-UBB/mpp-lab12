import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-rentals',
  templateUrl: './rentals.component.html',
  styleUrls: ['./rentals.component.css']
})
export class RentalsComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  add() {
    console.log("add rental button clicked");
    this.router.navigate(['rentals/new']);
  }

  filter(clientID: string, movieID: string) {
    console.log("filter rentals button clicked: clientID:", clientID, 'movieID:', movieID);
    this.router.navigate(['rentals/filter/:clientID/:movieID', {clientID: clientID, movieID: movieID}]);
  }
}
