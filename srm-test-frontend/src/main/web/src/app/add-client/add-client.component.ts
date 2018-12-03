import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators, AbstractControl } from "@angular/forms";
import { first } from "rxjs/operators";
import { Router } from "@angular/router";
import { ClientService } from "../services/client.service";
import { ClientTypeService } from "../services/client-type.service";
import { Client } from "../models/client.model";
import { ClientType } from "../models/client-type.model";

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private router: Router, private clientService: ClientService, private clientTypeService: ClientTypeService) { }

  clientTypes: ClientType[];
  addForm: FormGroup;
  clientTypeValue : ClientType = { id : 0,  description : 'Select' };

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      creditLimit: ['', Validators.required],
      clientType: ['Select', Validators.pattern]
    });

    this.clientTypeService.getClientTypes()
      .subscribe( data => {
        this.clientTypes = data;
    });

  }

  onClientTypeSelect(clientType : ClientType) {
        this.clientTypeValue = clientType;
        this.addForm.patchValue({
          clientType: this.clientTypeValue.description
        });
  }

  onClientTypeUnselected() {
        this.clientTypeValue = { id : 0,  description : 'Select' };
        this.addForm.patchValue({
          clientType: this.clientTypeValue.description
        });
  }

  onSubmit() {

    if (this.addForm.invalid) {
       return;
    }

    this.clientService.createClient(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['list-client']);
      });
  }

}
