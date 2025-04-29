import { Injectable } from '@angular/core';
import { env } from '../env/env.test';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../models/Employee';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private baseUrl = `${env.baseUrl}/employee`;

  constructor(private http: HttpClient) {}

  loadEmployeeData(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/all`);
  }

  register(data:any) {
    return this.http.post(`${this.baseUrl}/register`, data, {
      responseType: 'text',
    });
  }
}
