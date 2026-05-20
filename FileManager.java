
package com.mycompany.studentdatamanager;

import java.io.*;
import java.util.List;

public class FileManager {

    // دالة لحفظ البيانات في ملف
    public static void saveData(List<Student> students, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(students); // حفظ قائمة الطلاب
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // دالة لتحميل البيانات من ملف
    public static List<Student> loadData(String filename) {
        List<Student> students = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) in.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[FileManager] Error loading data: " + e.getMessage());
        }
        return students;
    }

    // إضافة "خيط إغلاق" لحفظ البيانات عند إغلاق البرنامج
    public static void addShutdownHook(List<Student> students, String filename) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveData(students, filename);
            System.out.println("Data saved successfully!");
        }));
    }
}
