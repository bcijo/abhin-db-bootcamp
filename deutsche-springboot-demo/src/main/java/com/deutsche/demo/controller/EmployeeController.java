package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService empService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        return empService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") Integer id) {
        logger.debug("Fetching employee with ID: {}", id);
        return empService.getEmployeeById(id);
    }

    @PostMapping
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        logger.info("Adding new employee: {}", employee.getName());
        return empService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        logger.warn("Attempting to delete employee with ID: {}", id);
        empService.deleteEmployee(id);
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
    }
}