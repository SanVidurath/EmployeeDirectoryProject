import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from '../../service/EmployeeService';
import { Employee } from '../../models/Employee';

@Component({
  selector: 'app-get-all-employees',
  imports: [CommonModule],
  templateUrl: './get-all-employees.component.html',
  styleUrl: './get-all-employees.component.css'
})
export class GetAllEmployeesComponent implements OnInit{
  constructor(
    private http:HttpClient,
    private employeeServie:EmployeeService
  ){}

  ngOnInit(): void {
    this.getEmployeeData();
  }

  listOfEmployees:Employee[]=[];

  getEmployeeData() {
    this.employeeServie.loadEmployeeData().subscribe((res) => {
      this.listOfEmployees = res;
    });
  }
}
