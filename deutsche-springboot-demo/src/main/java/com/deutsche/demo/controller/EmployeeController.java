package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeController {

    private List<Employee> empList = new ArrayList<>();
    @Autowired
    private EmployeeService empService = new EmployeeService();

    // Constructor to pre-populate some data
    public EmployeeController() {
        empList.add(new Employee(101, "Alice", 100000.0));
    }

    @GetMapping("emp")
    public List<Employee> getAllEmployees() {

        return empService.getAllEmployees();
    }

    @GetMapping("emp/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") Integer Id) {

        return empService.getEmployeeById(Id);
    }

    //    http://localhost:8080/api/emp
    @PostMapping("emp")
    public Employee addEmployee(@RequestBody Employee employee) {
        return empService.addEmployee(employee);
    }

    @DeleteMapping("emp/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        boolean deleted = empService.deleteEmployee(id);
        return deleted
                ? "Employee with ID " + id + " deleted successfully."
                : "Employee with ID " + id + " not found.";
    }
}
