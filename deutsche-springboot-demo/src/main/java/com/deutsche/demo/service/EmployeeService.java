package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepository;

    public List<Employee> getAllEmployees() {
        return empRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return empRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee addEmployee(Employee employee) {
        return empRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        if (empRepository.existsById(id)) {
            empRepository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}