# Student Record Management System (SRMS)

## 🚀 How to Run the Project  
1. Open Command Line (CMD / Terminal) and navigate to the project root folder:
   cd "Path_To_Your_Project_Folder"

2. Compile all Java files inside the package structure:
   javac com/mycompany/studentdatamanager/*.java

3. Run the main application class:
   java com.mycompany.studentdatamanager.Main

---

## ✨ System Features
* Add, update, and remove student records.
* Support for both Undergraduate and Graduate students.
* Automated reporting (by GPA, Department, or Year).
* Multi-threaded background Auto-Save mechanism.
* Custom Exception Handling (SRMSException).

## 🧩 OOP Concepts Applied
* Inheritance (Student -> GraduateStudent)
* Polymorphism & Method Overriding
* Encapsulation (Private fields with Getters/Setters)
* Composition (Student with ContactInfo)

## 📁 Main Classes
* Main.java (Application entry point & Console Menu)
* Student.java & GraduateStudent.java (Data models)
* StudentManager.java (Core business logic)
* AutoSaveThread.java (Multithreading handler)
* FileManager.java (File I/O operations)
* ContactInfo.java (Manages encapsulated email and phone data)
* SRMSException.java (Base custom exception handler)

## 🛠️ Tools & Versions Used
* Java Development Kit (JDK) - Version 25
* NetBeans IDE - Version 28
* GitHub (For version control and collaboration)
