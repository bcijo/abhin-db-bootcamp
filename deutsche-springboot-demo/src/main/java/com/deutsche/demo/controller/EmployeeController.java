package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private List<Employee> empList = new ArrayList<>();
    @Autowired
    private EmployeeService empService = new EmployeeService();

    // Constructor to pre-populate some data
    public EmployeeController() {
        empList.add(new Employee(101, "Alice", 100000.0));
    }

    @GetMapping
    public List<Employee> getAllEmployees() {

        return empService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") Integer Id) {

        return empService.getEmployeeById(Id);
    }

    //    http://localhost:8080/api/emp
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
