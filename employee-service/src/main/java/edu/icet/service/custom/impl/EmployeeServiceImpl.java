package edu.icet.service.custom.impl;

import edu.icet.repository.custom.EmployeeRepository;
import edu.icet.service.custom.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
}
