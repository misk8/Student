public class Student {
    private String name;
    private String studentId;
    private double gpa;
    private String department;

    // Constructor
    public Student(String name, String studentId, double gpa, String department) {
        this.name = name;
        this.studentId = studentId;
        this.gpa = gpa;
        this.department = department;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name + ", GPA: " + gpa + ", Department: " + department;
    }
}