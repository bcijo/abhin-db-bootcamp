package com.db.maven.demo.service;

import com.db.maven.demo.model.Employee;
import com.db.maven.demo.util.DbUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeService {

//    private List<Employee> tempEmpList = new ArrayList<>(Arrays.asList(
//            new Employee(101, "Sonu", 90000.0),
//            new Employee(102, "Monu", 95000.0),
//            new Employee(103, "Tonu", 85000.0)));

    public List<Employee> fetchAllEmployees() {
        System.out.println("fetchAllEmployees");
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary")
                );
                employees.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // You can replace with logger
        }

        return employees;
//        return tempEmpList;
    }
}


