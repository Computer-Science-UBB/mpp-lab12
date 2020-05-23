import {Component, OnInit} from '@angular/core';
import {Router, ROUTER_CONFIGURATION} from "@angular/router";
import {ClientsFilterComponent} from "./clients-filter/clients-filter.component";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  add() {
    console.log("add client button clicked");
    this.router.navigate(['clients/new']);
  }

  filter(firstname: string, secondname: string, job: string, age:string) {
    console.log("filter button clicked: firstname:", firstname, " secondname:", secondname, " job:", job, " age:", age);
    this.router.navigate(['clients/filter/:firstname/:secondname/:job/:age', {
      firstname: firstname,
      secondname: secondname,
      job: job,
      age: age
    }]);
  }
}
