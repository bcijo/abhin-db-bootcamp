package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    @Transactional
    void setUp() {
        employeeRepository.deleteAll();
        Employee employee = new Employee(null, "Alice", 100000.0);
        employeeRepository.save(employee);
    }

    @Test
    void getAllEmployees_ShouldReturnList() {
        List<Employee> employees = employeeService.getAllEmployees();
        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());
    }

    @Test
    void getEmployeeById_ShouldReturnEmployee() {
        Employee employee = employeeService.getEmployeeById(1);
        assertNotNull(employee);
        assertEquals("Alice", employee.getName());
    }

    @Test
    void getEmployeeById_ShouldThrowExceptionWhenNotFound() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(999));
    }

    @Test
    void addEmployee_ShouldSaveNewEmployee() {
        Employee newEmployee = new Employee(null, "Bob", 90000.0);
        Employee savedEmployee = employeeService.addEmployee(newEmployee);
        assertNotNull(savedEmployee.getId());
        assertEquals("Bob", savedEmployee.getName());
    }

    @Test
    void addEmployee_ShouldThrowExceptionWhenIdSet() {
        Employee employeeWithId = new Employee(1, "Charlie", 80000.0);
        assertThrows(IllegalArgumentException.class, () -> employeeService.addEmployee(employeeWithId));
    }

    @Test
    void deleteEmployee_ShouldDeleteExistingEmployee() {
        employeeService.deleteEmployee(1);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(1));
    }

    @Test
    void deleteEmployee_ShouldThrowExceptionWhenNotFound() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(999));
    }

    @Test
    void findEmployeeByName_ShouldReturnMatchingEmployees() {
        List<Employee> employees = employeeService.findEmployeeByName("Alice");
        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());
        assertEquals("Alice", employees.get(0).getName());
    }

    @Test
    void findEmployeeByName_ShouldThrowExceptionWhenNoMatch() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployeeByName("Charlie"));
    }

    @Test
    void findEmployeeBySalaryGreaterThan_ShouldReturnMatchingEmployees() {
        List<Employee> employees = employeeService.findEmployeeBySalaryGreaterThan(50000.0);
        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());
        assertEquals("Alice", employees.get(0).getName());
    }

    @Test
    void findEmployeeBySalaryGreaterThan_ShouldThrowExceptionWhenNoMatch() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployeeBySalaryGreaterThan(150000.0));
    }
}