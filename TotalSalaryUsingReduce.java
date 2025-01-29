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

public class TotalSalaryUsingReduce {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 7000),
                new Employee(2, "Bob", 5500),
                new Employee(3, "Charlie", 4000),
                new Employee(4, "David", 9000),
                new Employee(5, "Eve", 3000));

        // Calculate Total Salary Using Reduce
        double totalSalary = employees.stream()
                .map(Employee::salary) // Extract the salary of each employee
                .reduce(0.0, (sum, salary) -> sum + salary); // Accumulate salaries

        // Print the total salary
        System.out.println("Total Salary of All Employees: " + totalSalary);
    }
}
