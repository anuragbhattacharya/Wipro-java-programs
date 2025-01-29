import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", salary=" + salary + '}';
    }
}

public class EmployeeOperations {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 7000),
                new Employee(2, "Bob", 5500),
                new Employee(3, "Charlie", 4000),
                new Employee(4, "David", 9000),
                new Employee(5, "Eve", 3000)
        );

        // 1. Find Employee with Second Highest Salary
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary).reversed()) // Sort by salary descending
                .skip(1) // Skip the first (highest paid) employee
                .findFirst() // Get the second highest
                .ifPresent(emp -> System.out.println("Second Highest Paid Employee: " + emp));

        // 2. Partition Employees by Salary Threshold (e.g., greater than 5000)
        Map<Boolean, List<Employee>> partitioned = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.salary > 5000));

        // Print partitioned employees
        System.out.println("\nEmployees with Salary Greater than 5000:");
        partitioned.get(true).forEach(System.out::println);

        System.out.println("\nEmployees with Salary 5000 or Below:");
        partitioned.get(false).forEach(System.out::println);
    }
}
