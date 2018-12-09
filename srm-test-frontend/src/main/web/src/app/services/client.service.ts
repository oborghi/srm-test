import { Injectable } from '@angular/core';
import { Client } from "../models/client.model";
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'

})
export class ClientService {

  constructor(private http: HttpClient) { }

    baseUrl: string = "api/client";

    getClients() {
      return this.http.get<Client[]>(this.baseUrl + '/all');
    }

    getClientById(id: number) {
      return this.http.get<Client>(this.baseUrl + '/' + id);
    }

    createClient(client: Client) {
      return this.http.post(this.baseUrl + '/add', client);
    }

    updateClient(client: Client) {
      return this.http.put(this.baseUrl + '/update', client);
    }

    deleteClient(id: number) {
      return this.http.delete(this.baseUrl + '/' + id);
    }

    deleteAllClients(id: number) {
      return this.http.delete(this.baseUrl + '/all');
    }
}
