package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceMockTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees_ShouldReturnList() {
        List<Employee> employees = List.of(new Employee(1, "Alice", 100000.0));
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getEmployeeById_ShouldReturnEmployee() {
        Employee employee = new Employee(1, "Alice", 100000.0);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1);
        assertNotNull(result);
        assertEquals("Alice", result.getName());
        verify(employeeRepository, times(1)).findById(1);
    }

    @Test
    void getEmployeeById_ShouldThrowExceptionWhenNotFound() {
        when(employeeRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(999));
        verify(employeeRepository, times(1)).findById(999);
    }

    @Test
    void addEmployee_ShouldSaveNewEmployee() {
        Employee employee = new Employee(null, "Bob", 90000.0);
        when(employeeRepository.save(employee)).thenReturn(new Employee(2, "Bob", 90000.0));

        Employee result = employeeService.addEmployee(employee);
        assertNotNull(result.getId());
        assertEquals("Bob", result.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void addEmployee_ShouldThrowExceptionWhenIdSet() {
        Employee employeeWithId = new Employee(1, "Charlie", 80000.0);

        assertThrows(IllegalArgumentException.class, () -> employeeService.addEmployee(employeeWithId));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void deleteEmployee_ShouldDeleteExistingEmployee() {
        when(employeeRepository.existsById(1)).thenReturn(true);

        employeeService.deleteEmployee(1);
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteEmployee_ShouldThrowExceptionWhenNotFound() {
        when(employeeRepository.existsById(999)).thenReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(999));
        verify(employeeRepository, never()).deleteById(999);
    }

    @Test
    void findEmployeeByName_ShouldReturnMatchingEmployees() {
        List<Employee> employees = List.of(new Employee(1, "Alice", 100000.0));
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.findEmployeeByName("Ali");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void findEmployeeByName_ShouldThrowExceptionWhenNoMatch() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployeeByName("Charlie"));
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void findEmployeeBySalaryGreaterThan_ShouldReturnMatchingEmployees() {
        List<Employee> employees = List.of(new Employee(1, "Alice", 100000.0));
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.findEmployeeBySalaryGreaterThan(50000.0);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void findEmployeeBySalaryGreaterThan_ShouldThrowExceptionWhenNoMatch() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployeeBySalaryGreaterThan(150000.0));
        verify(employeeRepository, times(1)).findAll();
    }
}