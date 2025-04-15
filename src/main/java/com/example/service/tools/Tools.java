package com.example.service.tools;

import com.example.service.Util;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public  class Tools {
    private Util util = new Util();
    @Tool(description = """
            Gives student details for a given student ID.
            """)
    Student studentDetails(@ToolParam(description = "Student ID", required = true) StudentId studentId) {
        if (studentId == null) {
            return null;
        }
        System.out.println("Fetching details for student ID: " + studentId);
        Student studentDetail = util.getStudentDetails(studentId.studentId());
        System.out.println("Student Details: " + studentDetail);


        return studentDetail;
    }



    @Tool(description = "Lists all student IDs as [StudentInfo] contains only ids")
    List<Student> listStudentIds() {
        String studentIds = util.getStudentIds();
        System.out.println("Student IDs: " + studentIds);
        String[] ids = studentIds.split(",");
        List<Student> studentInfos = new ArrayList<>();
        for(String id : ids) {
            studentInfos.add(new Student(null, 0, 0.0, id));
        }

        return studentInfos;
    }


}