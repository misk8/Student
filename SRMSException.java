package com.mycompany.studentdatamanager;

// Exceptions.java
// 1. استثناء عام للنظام
class SRMSException extends Exception {

    public SRMSException(String message) {
        super(message);
    }
}

// 2. الطالب موجود مسبقاً
class DuplicateStudentException extends SRMSException {

    public DuplicateStudentException(String studentId) {
        super("Error: Student with ID '" + studentId + "' already exists.");
    }
}

// 3. الطالب غير موجود
class StudentNotFoundException extends SRMSException {

    public StudentNotFoundException(String studentId) {
        super("Error: Student with ID '" + studentId + "' was not found.");
    }
}

// 4. معدل غير صحيح
class InvalidGPAException extends SRMSException {

    public InvalidGPAException(double gpa) {
        super("Error: Invalid GPA '" + gpa + "'. GPA must be between 0.0 and 5.0.");
    }
}

// 5. إدخال فارغ أو خاطئ
class InvalidInputException extends SRMSException {

    public InvalidInputException(String fieldName) {
        super("Error: '" + fieldName + "' cannot be empty or null.");
    }
}
