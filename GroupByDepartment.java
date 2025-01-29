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

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' +
                ", salary=" + salary + ", department='" + department + '\'' + '}';
    }
}

public class GroupByDepartment {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 7000, "IT"),
                new Employee(2, "Bob", 5500, "Finance"),
                new Employee(3, "Charlie", 4000, "HR"),
                new Employee(4, "David", 9000, "IT"),
                new Employee(5, "Eve", 3000, "Finance")
        );

        // Group Employees by Department
        Map<String, List<Employee>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(emp -> emp.department));

        // Print grouped employees
        employeesByDept.forEach((dept, empList) -> {
            System.out.println("\nDepartment: " + dept);
            empList.forEach(System.out::println);
        });
    }
}
