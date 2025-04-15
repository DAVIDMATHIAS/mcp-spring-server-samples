package com.example.service;

import com.example.service.tools.Student;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Util {
    Map<String, Student> studentDetails = new HashMap<>();
    public Student getStudentDetails(String studentId) {
        if (studentDetails.containsKey(studentId)) {
            return studentDetails.get(studentId);
        }
        try {
            File file = new File("StudentRecords.csv");
            java.util.Scanner scanner = new java.util.Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",\\s*");

                if (parts[0].equals(studentId)) {
                    String studentDetail = String.format("Student ID: %s\nName: %s\nAge: %s\nGPA: %s",
                            parts[0], parts[1], parts[2], parts[3]);
                    Student studentInfo = new Student(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), parts[0]);
                    studentDetails.put(studentId, studentInfo);
                    return studentInfo;
                }
            }
            scanner.close();
            return null;

        } catch (java.io.FileNotFoundException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Util tools = new Util();
        String[] ids = tools.getStudentIds().split(",");
        for (String id : ids) {
            System.out.println("Student ID: " + id);
            System.out.println(tools.getStudentDetails(id));
        }
    }

    public String getStudentIds() {
        if (studentDetails.isEmpty()) {
            try {
                File file = new File("StudentRecords.csv");
                java.util.Scanner scanner = new java.util.Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",\\s*");
                    Student studentInfo = new Student(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), parts[0]);
                    studentDetails.put(parts[0], studentInfo);
                }
                scanner.close();
            } catch (java.io.FileNotFoundException e) {
                return "Error: Student records file not found";
            }
        }

        return String.join(",", studentDetails.keySet());
    }
}
