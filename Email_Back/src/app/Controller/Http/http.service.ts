import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private _url: string = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  postRequest(instruction: string, requestBody?: any): Observable<any> {
    return this.http.post(`${this._url}${instruction}`, requestBody, {responseType: 'text'});
  }

  getRequest(instruction: string, requestBody?: any): Observable<any> {
    return this.http.get(`${this._url}${instruction}`, {params: {requestBody}, responseType: 'text'});
  }

  putRequest(instruction: string, requestBody?: any): Observable<any> {
    return this.http.put(`${this._url}${instruction}`, requestBody);
  }

}