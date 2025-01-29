import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}

public class EmployeeDetails {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 7000),
                new Employee(2, "Bob", 5500),
                new Employee(3, "Charlie", 4000),
                new Employee(4, "David", 9000),
                new Employee(5, "Eve", 3000)
        );

        // 1. Get Employee Names and Salaries
        System.out.println("Employee Names and Salaries:");
        employees.forEach(emp -> System.out.println(emp.name + " - " + emp.salary));

        // 2. Count Employees
        long count = employees.size();
        System.out.println("\nTotal Number of Employees: " + count);
    }
}
