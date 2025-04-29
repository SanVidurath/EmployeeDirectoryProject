package edu.icet.service.custom.impl;

import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.repository.custom.EmployeeRepository;
import edu.icet.service.custom.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public void register(Employee employee) {
        EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
    }

    @Override
    public List<Employee> getAll() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        employeeEntityList.forEach(employeeEntity ->
                employeeList.add(modelMapper.map(employeeEntity, Employee.class)));

        return employeeList;
    }
}
