package com.db.maven.demo.service;

import com.db.maven.demo.model.Employee;
import com.db.maven.demo.util.DbUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceMockitoTest {

    private Connection mockConnection;
    private ResultSet mockResultSet;
    private EmployeeService service;

    @BeforeEach
    void setup() throws SQLException {
        mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    void testFetchAllEmployeesWithMockito() throws SQLException {

        when(mockResultSet.next()).thenReturn(true, true, true, false);

        when(mockResultSet.getInt("id")).thenReturn(101, 102, 103);
        when(mockResultSet.getString("name")).thenReturn("Sonu", "Monu", "Tonu");
        when(mockResultSet.getDouble("salary")).thenReturn(90000.0, 95000.0, 85000.0);

        try (MockedStatic<DbUtil> dbUtilMockedStatic = mockStatic(DbUtil.class)) {
            dbUtilMockedStatic.when(DbUtil::getConnection).thenReturn(mockConnection);

            service = new EmployeeService(); // real service
            List<Employee> employees = service.fetchAllEmployees();

            employees.forEach(System.out::println);

            assertEquals(3, employees.size());
            assertEquals("Sonu", employees.get(0).getName());
            assertEquals(102, employees.get(1).getId());
            assertEquals(85000.0, employees.get(2).getSalary());
        }
    }
}


//package com.db.maven.demo.service;
//
//import com.db.maven.demo.model.Employee;
//import com.db.maven.demo.util.DbUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
/// /reference
/// /https://site.mockito.org/#how
//
//class EmployeeServiceMockitoTest {
//
//    private Connection mockConnection;
//    private ResultSet mockResultSet;
//    private static EmployeeService service;
//
//    @BeforeEach
//    void setup() throws SQLException {
//        service = new EmployeeService();
//
//        List<Employee> tempEmpList = new ArrayList<>(Arrays.asList(
//                new Employee(101, "Sonu", 90000.0),
//                new Employee(102, "Monu", 95000.0),
//                new Employee(103, "Tonu", 85000.0)));
//
//        mockConnection = mock(Connection.class);
//        PreparedStatement mockStatement = mock(PreparedStatement.class);
//        mockResultSet = mock(ResultSet.class);
//        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
//        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
//        when(service.fetchAllEmployees()).thenReturn(tempEmpList);
//    }
//
//    @Test
//    void getAllData() {
//        assertEquals(3, service.fetchAllEmployees().size());
//    }
//
//    @Test
//    void testFetchAllEmployeesWithMockito() throws SQLException {
//
//        when(mockResultSet.next()).thenReturn(true, true, true, false);
//
//        List<Employee> tempEmpList = new ArrayList<>(Arrays.asList(
//                new Employee(101, "Sonu", 90000.0),
//                new Employee(102, "Monu", 95000.0),
//                new Employee(103, "Tonu", 85000.0)));
//
////        when(mockResultSet).thenReturn(tempEmpList);
//
//
//        try (MockedStatic<DbUtil> dbUtilMockedStatic = mockStatic(DbUtil.class)) {
//            dbUtilMockedStatic.when(DbUtil::getConnection).thenReturn(mockConnection);
//
//            EmployeeService service = new EmployeeService();
//            List<Employee> employees = service.fetchAllEmployees();
//            employees.forEach(System.out::println); //
//
//            assertEquals(10, employees.size(), "Wrong!" );
//            assertNotNull( employees);
//
//        }
//    }
//}
