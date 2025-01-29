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

public class EmployeeSalaryOperations {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 7000),
                new Employee(2, "Bob", 5500),
                new Employee(3, "Charlie", 4000),
                new Employee(4, "David", 9000),
                new Employee(5, "Eve", 3000)
        );

        // 1. Calculate Average Salary
        double avgSalary = employees.stream()
                .mapToDouble(emp -> emp.salary)
                .average()
                .orElse(0);
        System.out.println("Average Salary: " + avgSalary);

        // 2. Sort Employees by Salary (Ascending Order)
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(emp -> emp.salary))
                .collect(Collectors.toList());

        // Print sorted employees
        System.out.println("\nEmployees Sorted by Salary:");
        sortedEmployees.forEach(System.out::println);
    }
}
