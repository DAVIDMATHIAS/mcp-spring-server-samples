package com.example.service.tools;

import com.example.service.Util;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public  class Tools {
    private Util util = new Util();
    @Tool(description = "Gives details of a student, like name, age, GPA. \n calling listStudentIds() will give you a list of student IDs")
    String studentDetails(@ToolParam(description = "Student ID") String studentId) {
        if (studentId == null || studentId.isEmpty()) {
            return "Student ID cannot be null or empty";
        }
        System.out.println("Fetching details for student ID: " + studentId);
        String studentDetail = util.getStudentDetails(studentId);
        if (studentDetail == null || studentDetail.isEmpty()) {
            return "No details found for student ID: " + studentId;
        }
        System.out.println("Student Details: " + studentDetail);

        return studentDetail;
    }



    @Tool(description = "Lists all student IDs in comma separated format")
    String listStudentIds() {
        String ids = util.getStudentIds();
        System.out.println("Student IDs: " + ids);
        return ids;
    }


}