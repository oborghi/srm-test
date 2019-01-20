import { Component, OnInit } from '@angular/core';
import { Risk } from "../models/risk.model";
import { Router } from "@angular/router";
import { ClientService } from '../services/client.service';
import { RiskService } from '../services/risk.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {

  constructor(private router: Router
    , private clientService: ClientService
    , private riskService: RiskService
    , private formBuilder: FormBuilder) { }

  clientTypes: Risk[];
  addClientForm: FormGroup;
  submitted = false;

  ngOnInit() {
    this.riskService.getRisks()
      .subscribe( data => {
        this.clientTypes = data;
    });

    this.addClientForm = this.formBuilder.group({
        name: ['', Validators.required],
        creditLimit : ['', Validators.required],
        risk: ['Select', Validators.pattern(/^(?!.*Select).*$/)]
    });
  }

  get f() { return this.addClientForm.controls; }

  onRiskSelect(risk : Risk) {
    let riskValue = 'Select';
    if(risk != null) {
      riskValue = risk.description;
    }
    this.addClientForm.patchValue({
      risk: riskValue
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
