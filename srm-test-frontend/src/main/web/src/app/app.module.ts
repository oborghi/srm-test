import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routing } from "./app.routing";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { NgxCurrencyModule } from "ngx-currency";

import { AppComponent } from './app.component';
import { AddClientComponent } from './add-client/add-client.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { ListClientComponent } from './list-client/list-client.component';
import { ClientService } from './services/client.service';
import { ClientTypeService } from './services/client-type.service';

@NgModule({
  declarations: [
    AppComponent,
    AddClientComponent,
    EditClientComponent,
    ListClientComponent
  ],
  imports: [
      BrowserModule,
      routing,
      ReactiveFormsModule,
      FormsModule,
      HttpClientModule,
      NgxCurrencyModule
    ],
  providers: [ClientService, ClientTypeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
