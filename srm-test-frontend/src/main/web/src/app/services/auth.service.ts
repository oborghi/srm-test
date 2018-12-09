import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Credential } from "../models/credential.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authenticated = false;

    constructor(private http: HttpClient) {
    }

    authenticate(credential: Credential) {

          const headers = new HttpHeaders(credential ? {
              Authorization : 'Basic ' + btoa(credential.username + ':' + credential.password)
          } : {});

          return this.http.get('user', {headers: headers});
      }
}
