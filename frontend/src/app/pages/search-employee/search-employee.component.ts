import { Component } from '@angular/core';
import { Employee } from '../../models/Employee';
import { EmployeeService } from '../../service/EmployeeService';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search-employee',
  imports: [FormsModule, CommonModule],
  templateUrl: './search-employee.component.html',
  styleUrl: './search-employee.component.css'
})
export class SearchEmployeeComponent {
  searchType: string = '';
  searchValue: any = '';
  employees: Employee[] = [];

  constructor(private employeeService: EmployeeService) {}

  searchEmployee() {
    if (!this.searchValue) return;

    switch (this.searchType) {
      // case 'id':
      //   this.customerService
      //     .searchCustomerById(this.searchValue)
      //     .subscribe((res) => {
      //       res!==null?this.customers.push(res):this.customers=[];
      //       ;
      //     });

      //   break;
      // case 'name':
      //   this.customerService
      //     .searchCustomerByName(this.searchValue)
      //     .subscribe((res) => {
      //       this.customers = res;
      //     });

      //   break;
      // case 'address':
      //   this.customerService
      //     .searchCustomerByAddress(this.searchValue)
      //     .subscribe((res) => {
      //       this.customers = res;
      //     });

      //   break;
      // case 'salary':
      //   this.customerService
      //     .searchCustomerBySalary(this.searchValue)
      //     .subscribe((res) => {
      //       this.customers = res;
      //     });

      //   break;
    }
  }
}
