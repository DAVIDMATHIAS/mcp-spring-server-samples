package com.example.service.tools;

public record Student(String name, int age, double gpa, String studentId) {
    @Override
    public String toString() {
        return String.format("{\"studentId\":\"%s\",\"name\":\"%s\",\"age\":%d,\"gpa\":%.2f}",
                studentId, name, age, gpa);
    }
}
