// Modified by: Refal
package com.mycompany.studentdatamanager;

public class GraduateStudent extends Student {

    private static final long serialVersionUID = 2L;
    private final String researchTopic;
    private final String supervisor;

    public GraduateStudent(String name, String studentId, double gpa,
            String department, String email, String phone,
            String researchTopic, String supervisor) {
        super(name, studentId, gpa, department, email, phone);
        this.researchTopic = researchTopic;
        this.supervisor = supervisor;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    public String getSupervisor() {
        return supervisor;
    }

    @Override
    public String toString() {
        return super.toString() + " | Research: " + researchTopic + " | Supervisor: " + supervisor;
    }
}
