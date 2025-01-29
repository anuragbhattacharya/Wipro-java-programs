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

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", salary=" + salary + '}';
    }
}

public class EmployeeLongestName {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 7000),
                new Employee(2, "Bob", 5500),
                new Employee(3, "Charlotte", 4000),
                new Employee(4, "David", 9000),
                new Employee(5, "Eve", 3000)
        );

        // Find Employee with Longest Name
        employees.stream()
                .max(Comparator.comparingInt(emp -> emp.name.length()))
                .ifPresent(emp -> System.out.println("Employee with Longest Name: " + emp));
    }
}
