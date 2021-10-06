import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  private baseUrl = 'http://127.0.0.1:8080/api/v1/roless';

  constructor(private http: HttpClient) { }

  getRoles(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createRoles(roles: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, roles);
  }

  updateRoles(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteRoles(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getRolesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
