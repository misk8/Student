package com.mycompany.studentdatamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class StudentManager {

    // مصفوفة محمية ومزامنة بين الـ Threads وغير قابلة لإعادة التعيين
    private final List<Student> studentsList = Collections.synchronizedList(new ArrayList<>());

    public void addStudent(Student s) throws SRMSException {
        if (findStudentById(s.getStudentId()) != null) {
            throw new DuplicateStudentException(s.getStudentId());
        }
        studentsList.add(s);
        System.out.println("Student added successfully: " + s.getName());
    }

    public void displayHonorRoll() {
        System.out.println("--- Honor Roll Students (GPA >= 3.5) ---");
        synchronized (studentsList) { // حماية المصفوفة أثناء التكرار (Loop)
            for (Student s : studentsList) {
                if (s.getGpa() >= 3.5) {
                    System.out.println(s.toString());
                }
            }
        }
    }

    public Student findStudentById(String id) {
        synchronized (studentsList) {
            for (Student s : studentsList) {
                if (s.getStudentId().equals(id)) {
                    return s;
                }
            }
        }
        return null;
    }

    public void deleteStudent(String id) throws SRMSException {
        Student s = findStudentById(id);
        if (s == null) {
            throw new StudentNotFoundException(id);
        }
        studentsList.remove(s);
        System.out.println("Student with ID: " + id + " has been deleted.");
    }

    public void updateGpa(String id, double newGpa) throws SRMSException {
        Student s = findStudentById(id);
        if (s == null) {
            throw new StudentNotFoundException(id);
        }
        s.setGpa(newGpa);
        System.out.println("GPA updated successfully.");
    }

    public List<Student> getAllStudents() {
    synchronized (studentsList) {
        return new ArrayList<>(studentsList); // يُرجع نسخة مستقلة للحفظ بأمان
    }
}

    public void displayByDepartment(String dept) {
        System.out.println("--- Students in Department: " + dept + " ---");
        boolean found = false;
        synchronized (studentsList) {
            for (Student s : studentsList) {
                if (s.getDepartment().equalsIgnoreCase(dept)) {
                    System.out.println(s.toString());
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No students found in this department.");
        }
    }

}