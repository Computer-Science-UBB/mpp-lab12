import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "./client.model";
import {map} from "rxjs/operators";

@Injectable()
export class ClientService {
  private clientsURL = 'http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) {
  }

  getALl(): Observable<Client[]> {
    return this.httpClient
      .get<Array<Client>>(this.clientsURL);
  }

  delete(id: number) {
    const deleteURL = `${this.clientsURL}/delete/${id}`;
    return this.httpClient.delete(deleteURL);
  }

  getClient(id: number) {
    return this.getALl()
      .pipe(map(clients => clients
        .find(client => client.id == id)));
  }

  update(client: Client) {
    const updateURL = `${this.clientsURL}/${client.id}`;
    return this.httpClient
      .put<Client>(updateURL, client);
  }

  save(client: Client): Observable<Client> {
    console.log("saving client: ", client);

    return this.httpClient
      .post<Client>(this.clientsURL, client);
  }

  filter(firstname: string, secondname: string, job: string,age: string, sortClient: Client): Observable<Client[]> {
    if (age.length == 0)
      age = '-1';
    if (firstname.length == 0)
      firstname = firstname + ' '
    if (secondname.length == 0)
      secondname = secondname + ' '
    if (job.length == 0)
      job = job + ' '
    const filterURL = `${this.clientsURL}/${firstname}/${secondname}/${job}/${+age}`
    return this.httpClient
      .post<Array<Client>>(filterURL, sortClient);
  }

  getPage(page: number) {
    const pageURL = `${this.clientsURL}/page?page=${page}`
    return this.httpClient.get(pageURL);
  }
}
