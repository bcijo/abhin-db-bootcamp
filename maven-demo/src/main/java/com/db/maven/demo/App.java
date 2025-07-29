package com.db.maven.demo;

import com.db.maven.demo.model.Employee;
import com.db.maven.demo.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

// tight coupling
// loose coupling
// object creation
// DI

// bean configurations -
// Annonation configuration
// XML
// Java

//@ComponentScan(basePackages = {"com.db.maven.demo.service", "com.db.maven.some.other.package"})
@ComponentScan
public class App {

    public static void main(String[] args) {

        // step 1 = context object
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

//        EmployeeService service = new EmployeeService();

        EmployeeService service = context.getBean(EmployeeService.class);

        List<Employee> employeeList = service.fetchAllEmployees();
        System.out.println("Employees data from the database:");
        employeeList.forEach(System.out::println);
    }
}



//package com.db.maven.demo;
//
//import com.db.maven.demo.model.Employee;
//import com.db.maven.demo.service.EmployeeService;
//
//import java.util.List;
//
//public class App {
//    public static void main(String[] args) {
//        EmployeeService service = new EmployeeService();
//        List<Employee> employeeList = service.fetchAllEmployees();
//        System.out.println("Employees data from the database:");
//        employeeList.forEach(employee -> System.out.println(employee));
//
//    }
//}
//

//package com.db.maven.demo;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class App {
//
//    public static void main(String[] args) {
//
//        String url = "jdbc:postgresql://localhost:5432/deutsche";
//        String user = "postgres";
//        String password = "root";
//        String query = "SELECT * FROM employees";
//
//        try (Connection conn = DriverManager.getConnection(url, user, password);
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            System.out.println("Employees data from the database:");
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                double salary = rs.getDouble("salary");
//
//                System.out.println(id + "\t" + name + "\t" + salary);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
////package com.db.maven.demo;
////
/////**
//// * @author Vaman Deshmukh
//// */
////
////public class App {
////
////    public static void main(String[] args) {
////
////        System.out.println("Hello World!");
////    }
////}
