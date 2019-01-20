import { Component, OnInit } from '@angular/core';
import { Risk } from "../models/risk.model";
import { Router } from "@angular/router";
import { ClientService } from '../services/client.service';
import { RiskService } from '../services/risk.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit {

  constructor(private router: Router
      , private clientService: ClientService
      , private RiskService: RiskService
      , private formBuilder: FormBuilder) { }

    Risks: Risk[];
    editClientForm: FormGroup;
    submitted = false;

    ngOnInit() {

      this.RiskService.getRisks()
        .subscribe( data => {
          this.Risks = data;
      });

      this.editClientForm = this.formBuilder.group({
          id: [''],
          name: ['', Validators.required],
          creditLimit : ['', Validators.required],
          risk: ['', Validators.pattern(/^(?!.*Select).*$/)]
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
            risk: data.risk
          });
      });
    }

    get f() { return this.editClientForm.controls; }

    onRiskSelect(risk : Risk) {
      let riskValue = 'Select';
      if(risk != null) {
        riskValue = risk.description;
      }
      this.editClientForm.patchValue({
        risk: riskValue
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
