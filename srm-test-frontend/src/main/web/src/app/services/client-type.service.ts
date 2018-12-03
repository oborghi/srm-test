import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ClientType } from "../models/client-type.model";

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
