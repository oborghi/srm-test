import { Component, OnInit } from '@angular/core';
import { Client } from "../models/client.model";
import { Router } from "@angular/router";
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-list-client',
  templateUrl: './list-client.component.html',
  styleUrls: ['./list-client.component.css']
})
export class ListClientComponent implements OnInit {

    clients: Client[];
    constructor(private router: Router, private clientService: ClientService) { }

    ngOnInit() {
      this.clientService.getClients()
        .subscribe( data => {
          this.clients = data;
      });
    }

    deleteClient(client: Client): void {
      this.clientService.deleteClient(client.id)
        .subscribe( data => {
          this.clients = this.clients.filter(c => c !== client);
        })
    };

    editClient(client: Client): void {
      localStorage.removeItem("editClientId");
      localStorage.setItem("editClientId", client.id.toString());
      this.router.navigate(['edit-client']);
    };

    addClient(): void {
      this.router.navigate(['add-client']);
    };
}
