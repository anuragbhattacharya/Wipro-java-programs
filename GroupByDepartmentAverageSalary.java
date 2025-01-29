import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    double salary;
    String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
}

public class GroupByDepartmentAverageSalary {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 7000, "IT"),
                new Employee(2, "Bob", 5500, "Finance"),
                new Employee(3, "Charlie", 4000, "HR"),
                new Employee(4, "David", 9000, "IT"),
                new Employee(5, "Eve", 3000, "Finance"));

        // Group Employees by Department and Calculate Average Salary
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        emp -> emp.department,
                        Collectors.averagingDouble(emp -> emp.salary)));

        // Print Department-wise Average Salary
        avgSalaryByDept.forEach(
                (dept, avgSalary) -> System.out.println("Department: " + dept + ", Average Salary: " + avgSalary));
    }
}
