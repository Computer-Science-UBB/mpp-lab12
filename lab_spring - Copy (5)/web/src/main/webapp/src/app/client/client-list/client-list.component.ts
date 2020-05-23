import {Component, OnInit} from '@angular/core';
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-clients-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientsListComponent implements OnInit {
  clients: Client[];
  selectedClient: Client;
  pageNo: number = 0;
  pages: number[];

  constructor(private clientsService: ClientService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getPaged();
  }

  getPaged(){
    return this.clientsService.getPage(this.pageNo).subscribe(
      data => {
        this.clients = data['content'];
        this.pages = new Array(data['totalPages']+1);
        console.log(this.pages);
        console.log(data)
      },
      (error) => {
        console.log(error.error.message);
      }
    );
  }

  updateSelected(client: Client) {
    console.log("selected: ", client);
    this.selectedClient = client;
  }

  delete() {
    console.log("deleting client", this.selectedClient);
    this.clientsService.delete(this.selectedClient.id)
      .subscribe(_ => {
        console.log("done");

        this.clients = this.clients
          .filter(client => client.id !== this.selectedClient.id);
      })
  }


  update() {
    this.router.navigate(['clients/update/', this.selectedClient.id]);
  }

  setPage(i: number, $event: MouseEvent) {
    event.preventDefault();
    this.pageNo = i;
    this.getPaged();
  }
}
