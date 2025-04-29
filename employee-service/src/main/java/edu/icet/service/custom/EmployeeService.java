package edu.icet.service.custom;

import edu.icet.dto.Employee;

import java.util.List;

public interface EmployeeService {
    void register(Employee employee);
    List<Employee> getAll();
}
