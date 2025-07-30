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
        return empRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
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
        if (empRepository.existsById(id)) {
            empRepository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public List<Employee> findEmployeeByName(String name) {
        logger.debug("Searching for employees with name containing: {}", name);
        List<Employee> allEmployees = empRepository.findAll();
        List<Employee> matchingEmployees = allEmployees.stream()
                .filter(emp -> emp.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (matchingEmployees.isEmpty()) {
            throw new EmployeeNotFoundException(-1, name);
        }
        return matchingEmployees;
    }
}