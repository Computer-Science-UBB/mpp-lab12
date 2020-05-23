import {Component, OnInit} from '@angular/core';
import {ClientService} from "../shared/client.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-clients-new',
  templateUrl: './clients-new.component.html',
  styleUrls: ['./clients-new.component.css']
})
export class ClientsNewComponent implements OnInit {

  constructor(private clientService: ClientService,
              private location: Location) {
  }

  ngOnInit(): void {
  }

  save(firstname: string, secondname: string, job: string, age: string) {
    console.log("save client ", firstname, ' ', secondname, ' ', job, ' ', age);

    this.clientService.save({id: 0, firstname:firstname, secondname:secondname, job:job, age:+age})
      .subscribe(client => {
        console.log("saved client: ", client);
        this.back();
      });
  }

  back() {
    this.location.back();
  }
}
