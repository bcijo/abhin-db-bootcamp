package com.deutsche.demo.service;

import com.deutsche.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    private List<Employee> tempEmpList = new ArrayList<>(
            Arrays.asList(
                    new Employee(101, "Sonu", 90000.00),
                    new Employee(102, "Monu", 90000.00),
                    new Employee(103, "Tonu", 90000.00)));

    public List<Employee> getAllEmployees() {
        System.out.println(tempEmpList.size());
        return tempEmpList;
    }

    public Employee getEmployeeById(Integer id) {
        System.out.println(id);
        return tempEmpList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}