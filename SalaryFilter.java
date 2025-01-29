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

public class SalaryFilter {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 7000),
                new Employee(2, "Bob", 5500),
                new Employee(3, "Charlie", 4000),
                new Employee(4, "David", 9000),
                new Employee(5, "Eve", 3000));

        // Filter Employees with Salary > 5000
        List<Employee> filteredEmployees = employees.stream()
                .filter(emp -> emp.salary > 5000)
                .collect(Collectors.toList());

        // Print filtered employees
        System.out.println("Employees with Salary Greater Than 5000: " + filteredEmployees);
    }
}
