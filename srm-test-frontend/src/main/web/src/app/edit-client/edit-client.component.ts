import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { first } from "rxjs/operators";
import { Router } from "@angular/router";
import { ClientService } from "../services/client.service";
import { ClientTypeService } from "../services/client-type.service";
import { Client } from "../models/client.model";
import { ClientType } from "../models/client-type.model";

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private router: Router, private clientService: ClientService, private clientTypeService: ClientTypeService) { }

  clientTypes: ClientType[];
  editForm: FormGroup;
  clientTypeValue : ClientType = { id : 0,  description : 'Select' };

  ngOnInit() {

    let clientId = localStorage.getItem("editClientId");
    if(!clientId) {
      alert("Invalid action.");
      this.router.navigate(['list-client']);
      return;
    }

    this.clientTypeService.getClientTypes()
      .subscribe( data => {
        this.clientTypes = data;
    });

    this.editForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      creditLimit: ['', Validators.required],
      clientType: ['Select', Validators.pattern]
    });

    this.clientService.getClientById(+clientId)
      .subscribe( data => {
        this.editForm.setValue(data);
        this.clientTypes.forEach(obj => {
           if(data.clientType === obj.description){
              this.clientTypeValue = obj;
           }
        });
    });
  }

  onClientTypeSelect(clientType : ClientType) {
        this.clientTypeValue = clientType;
        this.editForm.patchValue({
          clientType: this.clientTypeValue.description
        });
  }

  onClientTypeUnselected() {
        this.clientTypeValue = { id : 0,  description : 'Select' };
        this.editForm.patchValue({
          clientType: this.clientTypeValue.description
        });
  }

  onSubmit() {
    this.clientService.updateClient(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['list-client']);
        },
        error => {
          alert(error);
        });
  }

}
