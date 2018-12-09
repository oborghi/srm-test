import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { AuthService } from '../services/auth.service';
import { Credential } from "../models/credential.model";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;

      constructor(
          private router: Router,
          private authService : AuthService,
          private formBuilder : FormBuilder
      ) { }

      ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
      }

      get f() { return this.loginForm.controls; }

      onSubmit() {

          this.submitted = true;

          if (this.loginForm.invalid) {
              console.log("Passou!!");
              return;
          }

          this.authService.authenticate(this.loginForm.value).subscribe( data => {
              if(data['name']){
                this.router.navigate(['list-client']);
              }
          });
      }
}
