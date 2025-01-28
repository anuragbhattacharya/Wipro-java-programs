import java.util.*;

// Class to represent a Student
class Student {
    private String id;
    private String name;
    private List<Grade> grades;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double calculateGPA() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double totalPoints = 0;
        int totalCredits = 0;
        for (Grade grade : grades) {
            totalPoints += grade.getPoints();
            totalCredits += grade.getCourse().getCredits();
        }
        return totalPoints / totalCredits;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", GPA=" + calculateGPA() +
                '}';
    }
}

// Class to represent a Course
class Course {
    private String code;
    private String title;
    private int credits;

    public Course(String code, String title, int credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                '}';
    }
}

// Class to represent a Grade
class Grade {
    private Course course;
    private double grade;

    public Grade(Course course, double grade) {
        this.course = course;
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public double getGrade() {
        return grade;
    }

    public double getPoints() {
        return grade * course.getCredits();
    }

    @Override
    public String toString() {
        return "Grade{" +
                "course=" + course +
                ", grade=" + grade +
                '}';
    }
}

public class StudentGrade {
    private Map<String, Student> students;
    private Map<String, Course> courses;

    public StudentGrade() {
        students = new HashMap<>();
        courses = new HashMap<>();
    }

    public void addStudent(String id, String name) {
        students.put(id, new Student(id, name));
        System.out.println("Student added: " + name);
    }

    public void addCourse(String code, String title, int credits) {
        courses.put(code, new Course(code, title, credits));
        System.out.println("Course added: " + title);
    }

    public void assignGrade(String studentId, String courseCode, double grade) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        student.addGrade(new Grade(course, grade));
        System.out.println("Grade assigned: " + grade);
    }

    public void calculateGPA(String studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.printf("GPA of %s (%s): %.2f\n", student.getName(), student.getId(), student.calculateGPA());
    }

    public void viewStudents() {
        for (Student student : students.values()) {
            System.out.println(student);
        }
    }

    public void viewCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    public static void main(String[] args) {
        StudentGrade system = new StudentGrade();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nStudent Grade Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate GPA");
            System.out.println("5. View Students");
            System.out.println("6. View Courses");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.next();
                    system.addStudent(studentId, studentName);
                    break;
                case 2:
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.next();
                    System.out.print("Enter Course Title: ");
                    String courseTitle = scanner.next();
                    System.out.print("Enter Credits: ");
                    int credits = scanner.nextInt();
                    system.addCourse(courseCode, courseTitle, credits);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.next();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.next();
                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    system.assignGrade(studentId, courseCode, grade);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.next();
                    system.calculateGPA(studentId);
                    break;
                case 5:
                    system.viewStudents();
                    break;
                case 6:
                    system.viewCourses();
                    break;
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 7);
    }
}
