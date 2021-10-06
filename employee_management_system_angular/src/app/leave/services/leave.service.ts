import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Leave } from '../services/leave';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {

  private baseUrl = 'http://127.0.0.1:8080/api/v1/leaves';

  constructor(private http: HttpClient) { }

  getLeave(id: number): Observable<Leave> {
    return this.http.get<Leave>(`${this.baseUrl}/${id}`);
  }

  createLeave(leave: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, leave);
  }

  updateLeave(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteLeave(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getLeavesList(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/all-leaves/${id}`);
  }

  getLeavesCount(id: number): Observable<any> {
    console.log("Leaves calling")
    return this.http.get(`${this.baseUrl}/get-leaves-count/${id}`);
  }
}
