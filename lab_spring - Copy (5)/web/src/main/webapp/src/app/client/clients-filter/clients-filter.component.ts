import {Component, OnInit} from '@angular/core';
import {ClientService} from "../shared/client.service";
import {ActivatedRoute} from "@angular/router";
import {Client} from "../shared/client.model";
import {Location} from "@angular/common";

@Component({
  selector: 'app-clients-filter',
  templateUrl: './clients-filter.component.html',
  styleUrls: ['./clients-filter.component.css']
})
export class ClientsFilterComponent implements OnInit {

  clients: Client[];
  possibleOrientations = ['-', 'x']
  firstNameOrientation: number = 0;
  secondNameOrientation: number = 0;
  jobOrientation: number = 0;
  ageOrientation: number = 0;
  ascendingDescending: number = 0;

  constructor(private clientService: ClientService,
              private router: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.clientService.filter(
      this.router.snapshot.paramMap.get("firstname"),
      this.router.snapshot.paramMap.get("secondname"),
      this.router.snapshot.paramMap.get("job"),
      this.router.snapshot.paramMap.get("age"),
      {id: 0, firstname:"-", secondname:"-", job:"-", age:-1}
    ).subscribe(clients => this.clients = clients);
  }

  back() {
    this.location.back();
  }

  sort() {
    var ascendDescend = 0;
    var firstname = "-";
    var secondname = "-";
    var job = "-";
    var age = 0;

    if(this.possibleOrientations[this.firstNameOrientation] != firstname){
      firstname = this.possibleOrientations[this.firstNameOrientation];
    }

    if(this.possibleOrientations[this.secondNameOrientation] != secondname){
      secondname = this.possibleOrientations[this.secondNameOrientation];
    }

    if(this.possibleOrientations[this.jobOrientation] != job){
      job = this.possibleOrientations[this.jobOrientation];
    }

    if(this.possibleOrientations[this.ageOrientation] != '-'){
      age = 1;
    }

    if(this.possibleOrientations[this.ascendingDescending] == '-'){
      ascendDescend = -1
    }else{
      ascendDescend = 1;
    }

    this.clientService.filter(
      this.router.snapshot.paramMap.get("firstname"),
      this.router.snapshot.paramMap.get("secondname"),
      this.router.snapshot.paramMap.get("job"),
      this.router.snapshot.paramMap.get("age"),
      {id: ascendDescend, firstname: firstname, secondname: secondname, job: job, age: age}
    ).subscribe(clients => this.clients = clients);
  }

  changeFirstNameOrientation() {
    this.firstNameOrientation = (this.firstNameOrientation + 1) % 2
    console.log("firstname orientation:", this.possibleOrientations[this.firstNameOrientation])
  }

  changeSecondNameOrientation() {
    this.secondNameOrientation = (this.secondNameOrientation + 1) % 2
    console.log("secondname orientation:", this.possibleOrientations[this.secondNameOrientation])
  }

  changeJobOrientation() {
    this.jobOrientation = (this.jobOrientation + 1) % 2
    console.log("job orientation:", this.possibleOrientations[this.jobOrientation])
  }
  changeAgeOrientation() {
    this.ageOrientation = (this.ageOrientation + 1) % 2
    console.log("age orientation:", this.possibleOrientations[this.ageOrientation])
  }

  changeAscendingDescending(){
    this.ascendingDescending = (this.ascendingDescending + 1) % 2
    console.log("ascending/descending orientation:", this.possibleOrientations[this.ascendingDescending])
  }
}
