package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        logger.info("Adding new employee");
        return empRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee addEmployee(Employee employee) {
        logger.info("Adding new employee: {}", employee.getName());
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
}