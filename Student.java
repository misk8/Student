
package com.mycompany.studentdatamanager;

import java.io.Serializable;

public class Student implements Serializable {
private static final long serialVersionUID = 1L;
    private String name;
    private String studentId;
    private double gpa;
    private String department;
    private ContactInfo contactInfo;

    public Student(String name, String studentId, double gpa, String department, String email, String phone) {
        this.name = name;
        this.studentId = studentId;
        this.gpa = gpa;
        this.department = department;
        this.contactInfo = new ContactInfo(email, phone);
    }

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

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "ID: " + studentId + " | Name: " + name + " | GPA: " + gpa
                + " | Dept: " + department + " | " + contactInfo.toString();
    }
}
