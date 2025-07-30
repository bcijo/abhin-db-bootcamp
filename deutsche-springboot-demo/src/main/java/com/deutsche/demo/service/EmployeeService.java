package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmployeeRepository empRepository;

    public List<Employee> getAllEmployees() {
        logger.info("Retrieving all employees from the database");
        return empRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        logger.debug("Searching for employee with ID: {}", id);
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        return empRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Employee with ID {} not found", id);
                    return new EmployeeNotFoundException(id);
                });
    }

    public Employee addEmployee(Employee employee) {
        logger.info("Adding new employee: {}", employee.getName());
        if (employee.getId() != null) {
            throw new IllegalArgumentException("ID must not be set for new employees");
        }
        return empRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        logger.warn("Attempting to delete employee with ID: {}", id);
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        if (empRepository.existsById(id)) {
            empRepository.deleteById(id);
        } else {
            logger.warn("Employee with ID {} not found", id);
            throw new EmployeeNotFoundException(id);
        }
    }

    public List<Employee> findEmployeeByName(String name) {
        logger.debug("Searching for employees with name containing: {}", name);
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        List<Employee> employees = empRepository.findByNameIgnoreCase(name);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(-1, name);
        }
        return employees;
    }

    public List<Employee> findEmployeeBySalaryGreaterThan(Double salary) {
        logger.debug("Searching for employees with Salary greater than : {}", salary);
        if (salary == null) {
            throw new IllegalArgumentException("Salary must not be null");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary must be non-negative");
        }
        List<Employee> employees = empRepository.findBySalaryGreaterThan(salary);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(-1, salary);
        }
        return employees;
    }
}