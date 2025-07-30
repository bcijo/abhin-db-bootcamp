package com.deutsche.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Integer id) {
        super("Employee with the id " + id + " is not found!");
    }

    public EmployeeNotFoundException(Integer id, String name) {
        super(id == -1 ? "No employees found with name '" + name + "'" : "Employee with the id " + id + " is not found!");
    }
}