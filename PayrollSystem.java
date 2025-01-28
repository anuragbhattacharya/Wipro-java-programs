import java.util.ArrayList;
import java.util.List;

// Base Employee Class
abstract class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public abstract double calculateSalary();

    public abstract double calculateBonus();

    public void printDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + calculateSalary());
        System.out.println("Bonus: " + calculateBonus());
    }
}

// Permanent Employee Class
class PermanentEmployee extends Employee {
    private double baseSalary;

    public PermanentEmployee(int id, String name, String department, double baseSalary) {
        super(id, name, department);
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculateSalary() {
        return baseSalary;
    }

    @Override
    public double calculateBonus() {
        return baseSalary * 0.1; // 10% bonus
    }
}

// Contractual Employee Class
class ContractualEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public ContractualEmployee(int id, String name, String department, double hourlyRate, int hoursWorked) {
        super(id, name, department);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public double calculateBonus() {
        return 0; // No bonus for contractual employees
    }
}

// Department Class
class Department {
    private String name;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void generateReport() {
        System.out.println("\n--- Department: " + name + " ---");
        for (Employee employee : employees) {
            employee.printDetails();
            System.out.println("-------------------------");
        }
    }
}

// Main Class
public class PayrollSystem {
    public static void main(String[] args) {
        // Create Departments
        Department itDepartment = new Department("IT");
        Department hrDepartment = new Department("HR");

        // Add Employees
        PermanentEmployee permEmp1 = new PermanentEmployee(1, "Alice", "IT", 60000);
        PermanentEmployee permEmp2 = new PermanentEmployee(2, "Bob", "HR", 55000);

        ContractualEmployee contEmp1 = new ContractualEmployee(3, "Charlie", "IT", 50, 160);
        ContractualEmployee contEmp2 = new ContractualEmployee(4, "Diana", "HR", 45, 150);

        itDepartment.addEmployee(permEmp1);
        itDepartment.addEmployee(contEmp1);

        hrDepartment.addEmployee(permEmp2);
        hrDepartment.addEmployee(contEmp2);

        // Generate Reports
        itDepartment.generateReport();
        hrDepartment.generateReport();
    }
}
