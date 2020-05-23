import {Component, Input, OnInit} from '@angular/core';
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-clients-update',
  templateUrl: './clients-update.component.html',
  styleUrls: ['./clients-update.component.css']
})
export class ClientsUpdateComponent implements OnInit {

  @Input() client: Client;

  constructor(private clientService: ClientService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.clientService.getClient(+params['id'])))
      .subscribe(client => this.client = client);
  }

  back(): void {
    this.location.back();
  }


  save(firstname: string, secondname: string, job: string, age: string) {
    console.log("updating ", this.client, " => ", name)
    if (firstname.length != 0)
      this.client.firstname = firstname;
    if (secondname.length != 0)
      this.client.secondname = secondname;
    if (job.length != 0)
      this.client.job = job;
    if (age.length != 0)
      this.client.age = +age;

    this.clientService.update(this.client)
      .subscribe(_ => this.back());
  }
}
