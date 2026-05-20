//by misk
package com.mycompany.studentdatamanager;

import java.util.List;

// AutoSaveThread.java
public class AutoSaveThread extends Thread {

    private StudentManager studentManager;
    private String filename;
    private boolean running;
    private int intervalSeconds;

    public AutoSaveThread(StudentManager studentManager, String filename, int intervalSeconds) {
        this.studentManager = studentManager;
        this.filename = filename;
        this.intervalSeconds = intervalSeconds;
        this.running = true;
        this.setDaemon(true); // يتوقف تلقائياً عند إغلاق البرنامج
        this.setName("AutoSave-Thread");
    }

    @Override
    public void run() {
        System.out.println("[AutoSave] Auto-save started. Saving every " + intervalSeconds + " seconds.");

        while (running) {
            try {
                Thread.sleep(intervalSeconds * 1000L);

                if (running) {
                    // احفظ البيانات عبر FileManager
                    List<Student> students = studentManager.getAllStudents();
                    FileManager.saveData(students, filename);
                    System.out.println("\n [AutoSave] Records saved successfully at: "
                            + java.time.LocalTime.now().withNano(0));
                }

            } catch (InterruptedException e) {
                System.out.println("[AutoSave] Thread interrupted.");
                Thread.currentThread().interrupt();
                running = false;

            } catch (Exception e) {
                System.out.println("[AutoSave] Save failed: " + e.getMessage());
            }
        }

        System.out.println("[AutoSave] Auto-save stopped.");
    }

    public void stopAutoSave() {
        this.running = false;
        this.interrupt();
    }
}
