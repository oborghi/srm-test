import { Injectable } from '@angular/core';
import { Risk } from "../models/risk.model";
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'

})
export class RiskService {

  constructor(private http: HttpClient) { }

  baseUrl: string = "api/risk";

  getRisks() {
      return this.http.get<Risk[]>(this.baseUrl + '/all');
  }
}
