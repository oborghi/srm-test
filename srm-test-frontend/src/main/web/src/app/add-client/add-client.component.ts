import { Component, OnInit } from '@angular/core';
import { ClientType } from "../models/client-type.model";
import { Router } from "@angular/router";
import { ClientService } from '../services/client.service';
import { ClientTypeService } from '../services/client-type.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {

  constructor(private router: Router
    , private clientService: ClientService
    , private clientTypeService: ClientTypeService
    , private formBuilder: FormBuilder) { }

  clientTypes: ClientType[];
  addClientForm: FormGroup;
  submitted = false;

  ngOnInit() {
    this.clientTypeService.getClientTypes()
      .subscribe( data => {
        this.clientTypes = data;
    });

    this.addClientForm = this.formBuilder.group({
        name: ['', Validators.required],
        creditLimit : ['', Validators.required],
        clientType: ['Select', Validators.pattern(/^(?!.*Select).*$/)]
    });
  }

  get f() { return this.addClientForm.controls; }

  onClientTypeSelect(clientType : ClientType) {
    let clientTypeValue = 'Select';
    if(clientType != null) {
      clientTypeValue = clientType.description;
    }
    this.addClientForm.patchValue({
      clientType: clientTypeValue
    });
  }

  onSubmit() {
    this.submitted = true;

    if (this.addClientForm.invalid) {
        return;
    }

    this.clientService.createClient(this.addClientForm.value)
      .subscribe( data => {
        this.router.navigate(['list-client']);
      });
  }

  back() {
    this.router.navigate(['list-client']);
  }
}
