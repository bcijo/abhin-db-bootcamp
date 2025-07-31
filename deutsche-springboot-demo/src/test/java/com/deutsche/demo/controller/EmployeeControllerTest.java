package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService.addEmployee(new Employee(null, "Alice", 100000.0));
    }

    @Test
    void getAllEmployees_ShouldReturnList() throws Exception {
        mockMvc.perform(get("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void getEmployeeById_ShouldReturnEmployee() throws Exception {
        mockMvc.perform(get("/api/v1/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void getEmployeeById_ShouldReturn404WhenNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/employee/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void addEmployee_ShouldSaveNewEmployee() throws Exception {
        String employeeJson = "{\"name\":\"Bob\",\"salary\":90000.0}";
        mockMvc.perform(post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    void addEmployee_ShouldReturn400WhenIdSet() throws Exception {
        String employeeJson = "{\"id\":1,\"name\":\"Charlie\",\"salary\":80000.0}";
        mockMvc.perform(post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEmployee_ShouldDeleteExistingEmployee() throws Exception {
        mockMvc.perform(delete("/api/v1/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/employee/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findEmployeeByName_ShouldReturnMatchingEmployees() throws Exception {
        mockMvc.perform(get("/api/v1/employee/name")
                        .param("name", "Ali")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void findEmployeeByName_ShouldReturn404WhenNoMatch() throws Exception {
        mockMvc.perform(get("/api/v1/employee/name")
                        .param("name", "Charlie")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}