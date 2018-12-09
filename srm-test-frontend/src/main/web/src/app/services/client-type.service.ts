import { Injectable } from '@angular/core';
import { ClientType } from "../models/client-type.model";
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'

})
export class ClientTypeService {

  constructor(private http: HttpClient) { }

  baseUrl: string = "api/clientType";

  getClientTypes() {
      return this.http.get<ClientType[]>(this.baseUrl + '/all');
  }
}
