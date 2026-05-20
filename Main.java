
//mayyas al-mohaimeed
package com.mycompany.studentdatamanager;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String FILENAME = "students.dat";
    private static StudentManager manager = new StudentManager();
    private static AutoSaveThread autoSaveThread;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("  Student Record Management System (SRMS) ");
        System.out.println("      Qassim University - IT Dept        ");

        // --- تحميل البيانات من الملف ---
        List<Student> loaded = FileManager.loadData(FILENAME);
        if (loaded != null) {
            for (Student s : loaded) {
                try {
                    manager.addStudent(s);
                } catch (SRMSException e) {
                    System.out.println("Warning: " + e.getMessage());
                }
            }
            System.out.println("Records loaded successfully. (" + loaded.size() + " students)");
        } else {
            System.out.println("No existing records found. Starting fresh.");
        }

        // --- تشغيل AutoSave Thread ---
        autoSaveThread = new AutoSaveThread(manager, FILENAME, 60);
        autoSaveThread.start();

        // --- حفظ عند الإغلاق ---
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            autoSaveThread.stopAutoSave();
            FileManager.saveData(manager.getAllStudents(), FILENAME);
            System.out.println("\n[System] Data saved. Goodbye!");
        }));

        // --- القائمة الرئيسية ---
        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        addStudent();
                        break;
                    case "2":
                        updateGPA();
                        break;
                    case "3":
                        deleteStudent();
                        break;
                    case "4":
                        searchStudent();
                        break;
                    case "5":
                        manager.displayHonorRoll();
                        break;
                    case "6":
                        viewAll();
                        break;
                    case "7":
                        reportByDept();
                        break;
                    
                    case "8":
                        addGradStudent();
                        break;
                    case "9":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SRMSException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // --- طباعة القائمة ---
    private static void printMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Add Student");
        System.out.println("2. Update GPA");
        System.out.println("3. Delete Student");
        System.out.println("4. Search Student");
        System.out.println("5. Honor Roll Report (GPA >= 3.5)");
        System.out.println("6. View All Students");
        System.out.println("7. Report by Department");
        
        System.out.println("8. Add Graduate Student");
        System.out.println("9. Exit");
        System.out.print("Enter choice: ");
    }

    // --- إضافة طالب مع Exception Handling ---
    private static void addStudent() throws SRMSException {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new InvalidInputException("Name");
        }

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            throw new InvalidInputException("Student ID");
        }

        System.out.print("Enter GPA (0.0 - 5.0): ");
        double gpa;
        try {
            gpa = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("GPA must be a number");
        }
        if (gpa < 0.0 || gpa > 5.0) {
            throw new InvalidGPAException(gpa);
        }

        System.out.print("Enter Department: ");
        String dept = scanner.nextLine().trim();
        if (dept.isEmpty()) {
            throw new InvalidInputException("Department");
        }
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine().trim();
Student s = new Student(name, id, gpa, dept, email, phone);
    manager.addStudent(s);
    }

    // --- تحديث المعدل مع Exception Handling ---
    private static void updateGPA() throws SRMSException {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            throw new InvalidInputException("Student ID");
        }

        System.out.print("Enter new GPA (0.0 - 5.0): ");
        double gpa;
        try {
            gpa = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("GPA must be a number");
        }
        if (gpa < 0.0 || gpa > 5.0) {
            throw new InvalidGPAException(gpa);
        }

        manager.updateGpa(id, gpa);
    }

    // --- حذف طالب ---
    private static void deleteStudent() throws SRMSException {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            throw new InvalidInputException("Student ID");
        }
        manager.deleteStudent(id);
    }

    // --- البحث عن طالب ---
    private static void searchStudent() throws SRMSException {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            throw new InvalidInputException("Student ID");
        }

        Student found = manager.findStudentById(id);
        if (found == null) {
            throw new StudentNotFoundException(id);
        }
        System.out.println("Found: " + found.toString());
    }

    // --- عرض جميع الطلاب ---
    private static void viewAll() {
        List<Student> all = manager.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        System.out.println("\n--- All Students (" + all.size() + ") ---");
        for (Student s : all) {
            System.out.println(s.toString());
        }
    }

    private static void reportByDept() throws SRMSException {
        System.out.print("Enter Department name: ");
        String dept = scanner.nextLine().trim();
        if (dept.isEmpty()) {
            throw new InvalidInputException("Department");
        }
        manager.displayByDepartment(dept);
    }

    private static void addGradStudent() throws SRMSException {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new InvalidInputException("Name");
        }

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            throw new InvalidInputException("Student ID");
        }

        System.out.print("Enter GPA (0.0 - 5.0): ");
        double gpa;
        try {
            gpa = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("GPA must be a number");
        }
        if (gpa < 0.0 || gpa > 5.0) {
            throw new InvalidGPAException(gpa);
        }

        System.out.print("Enter Department: ");
        String dept = scanner.nextLine().trim();
        if (dept.isEmpty()) {
            throw new InvalidInputException("Department");
        }

        System.out.print("Enter Research Topic: ");
        String topic = scanner.nextLine().trim();

        System.out.print("Enter Supervisor Name: ");
        String supervisor = scanner.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine().trim();

        GraduateStudent gs = new GraduateStudent(name, id, gpa, dept, email, phone, topic, supervisor);
        manager.addStudent(gs);
    }
}
