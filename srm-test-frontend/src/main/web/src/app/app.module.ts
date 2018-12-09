import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routing } from "./app.routing";
import { ReactiveFormsModule } from '@angular/forms';
import { NgxCurrencyModule } from "ngx-currency";
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AddClientComponent } from './add-client/add-client.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { ListClientComponent } from './list-client/list-client.component';
import { LoginComponent } from './login/login.component';
import { AuthInterceptorService } from './services/auth-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    AddClientComponent,
    EditClientComponent,
    ListClientComponent,
    LoginComponent
  ],
  imports: [
      BrowserModule,
      routing,
      ReactiveFormsModule,
      NgxCurrencyModule,
      HttpClientModule
    ],
  providers: [
    {
       provide : HTTP_INTERCEPTORS,
       useClass: AuthInterceptorService,
       multi   : true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
