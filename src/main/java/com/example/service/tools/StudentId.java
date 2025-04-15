package com.example.service.tools;

public record StudentId(String studentId) {
    @Override
    public String toString() {
        return String.format("{\"studentId\":\"%s\"}", studentId);
    }
}
