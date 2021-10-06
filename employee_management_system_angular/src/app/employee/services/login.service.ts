import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from './login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = 'http://127.0.0.1:8080/api/v1/login';

  constructor(private http: HttpClient) { }

  getLogin(id: number): Observable<Login> {
    return this.http.get<Login>(`${this.baseUrl}/${id}`);
  }

  createLogin(login: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/save`, login);
  }

  checkUserNameExits(username: String): Observable<Login> {
    return this.http.get<Login>(`${this.baseUrl}/check-username/${username}`);
  }

  updateLogin(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteLogin(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getLoginsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all-logins`);
  }
}
