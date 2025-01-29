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

public class Top3HighestPaidEmployees {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 7000),
                new Employee(2, "Bob", 5500),
                new Employee(3, "Charlie", 4000),
                new Employee(4, "David", 9000),
                new Employee(5, "Eve", 3000));

        // Find Top 3 Highest Paid Employees
        List<Employee> top3Employees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary).reversed()) // Sort by salary descending
                .limit(3) // Get the top 3
                .collect(Collectors.toList());

        // Print Top 3 Employees
        System.out.println("Top 3 Highest Paid Employees:");
        top3Employees.forEach(System.out::println);
    }
}
