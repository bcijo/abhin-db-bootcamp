package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return empService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") Integer id) {
        return empService.getEmployeeById(id);
    }

    @PostMapping
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        return empService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        boolean deleted = empService.deleteEmployee(id);
        return deleted
                ? "Employee with ID " + id + " deleted successfully."
                : "Employee with ID " + id + " not found.";
    }
}