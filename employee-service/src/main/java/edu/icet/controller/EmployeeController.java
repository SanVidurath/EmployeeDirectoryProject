package edu.icet.controller;

import edu.icet.dto.Employee;
import edu.icet.service.custom.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<Map<String, String>> addEmployee(@Valid @RequestBody Employee employee){
        employeeService.register(employee);
        Map<String, String> response = new HashMap<>();
        response.put("message", "employee registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
