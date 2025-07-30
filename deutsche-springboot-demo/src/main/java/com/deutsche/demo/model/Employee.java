package com.deutsche.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK values
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "name should be provided")
    @NotBlank(message = "name can not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters.")
    @Column(name = "name")
    private String name;

    @Positive(message = "Salary should be more than 0.")
    @Column(name = "salary")
    private Double salary;

    public Employee() {}
    public Employee(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
