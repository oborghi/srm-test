import { Component, OnInit } from '@angular/core';
import { ClientType } from "../models/client-type.model";
import { Router } from "@angular/router";
import { ClientService } from '../services/client.service';
import { ClientTypeService } from '../services/client-type.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit {

  constructor(private router: Router
      , private clientService: ClientService
      , private clientTypeService: ClientTypeService
      , private formBuilder: FormBuilder) { }

    clientTypes: ClientType[];
    editClientForm: FormGroup;
    submitted = false;

    ngOnInit() {

      this.clientTypeService.getClientTypes()
        .subscribe( data => {
          this.clientTypes = data;
      });

      this.editClientForm = this.formBuilder.group({
          id: [''],
          name: ['', Validators.required],
          creditLimit : ['', Validators.required],
          clientType: ['', Validators.pattern(/^(?!.*Select).*$/)]
      });

      let clientId = localStorage.getItem("editClientId");
      if(!clientId) {
        alert("Invalid action.");
        this.router.navigate(['list-client']);
        return;
      }

      this.clientService.getClientById(+clientId)
        .subscribe( data => {
          this.editClientForm.patchValue({
            id: data.id,
            name: data.name,
            creditLimit : data.creditLimit,
            clientType: data.clientType
          });
      });
    }

    get f() { return this.editClientForm.controls; }

    onClientTypeSelect(clientType : ClientType) {
      let clientTypeValue = 'Select';
      if(clientType != null) {
        clientTypeValue = clientType.description;
      }
      this.editClientForm.patchValue({
        clientType: clientTypeValue
      });
    }

    onSubmit() {
      this.submitted = true;

      if (this.editClientForm.invalid) {
          return;
      }

      this.clientService.updateClient(this.editClientForm.value)
        .subscribe( data => {
          this.router.navigate(['list-client']);
        });
    }

    back() {
      this.router.navigate(['list-client']);
    }
}
