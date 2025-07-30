package com.db.maven.demo.service;

import com.db.maven.demo.service.EmployeeService;
import com.db.maven.demo.model.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//reference
//https://docs.junit.org/current/user-guide/#writing-tests-assertions

class EmployeeServiceTest {

    private static EmployeeService service = new EmployeeService();
    private static List<Employee> empList;

    @BeforeAll
    static void setUp() {
        empList = service.fetchAllEmployees();
    }

    @AfterAll
    static void tearDown() {
        service = null;
    }

    @Test
    void testFetchAllEmployeesSize() {
        assertEquals(4, empList.size());
    }

    @Test
    void testFetchAllEmployeesSizePositive() {
        assertNotEquals(0, empList.size());
    }

    @Disabled
    @Test
    void testFetchAllEmployeesNotNull() {
        assertNotNull(empList);
    }

    @Test
    void testFetchAllEmployeesNotEmpty() {
        assertFalse(empList.isEmpty());
    }
}


//package com.db.maven.demo.service;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//class EmployeeServiceTest {
//
//    @Test
//    void testTest() {
//        int expected = 10;
//        int actual = 5 + 5;
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void testTest2() {
//        int expected = 10;
//        int actual = 5 + 6;
//        assertNotEquals(expected, actual);
//    }
//}
//
