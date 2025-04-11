package com.example.service;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ServiceApplication {
	public static final String FOLDER="/home/davidmathias/work/mcp/mcp-client-demo-01/user-context";
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	ToolCallbackProvider toolCallbackProvider(Tools tools){
		return MethodToolCallbackProvider.builder().toolObjects(tools).build();
	}


}

@Component
class Tools {
    @Tool(description = "List full details of a student")
    String studentDetails(@ToolParam(description = "Student ID") String studentId) {
		return getStudentDetails(studentId);
	}

	public static void main(String[] args) {
		Tools tools = new Tools();
		String[] ids = tools.getStudentIds().split(",");
		for (String id : ids) {
			System.out.println("Student ID: " + id);
			System.out.println(tools.getStudentDetails(id));
		}
	}
	Map<String,String> studentDetails = new HashMap<>();
	private  String getStudentDetails(String studentId) {
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
					studentDetails.put(studentId, studentDetail);
					return studentDetail;
				}
			}
			scanner.close();
			return "Student not found with ID: " + studentId;

		} catch (java.io.FileNotFoundException e) {
			return "Error: Student records file not found";
		}
	}
	@Tool(description = "List all student Ids")
	String listStudentIds() {
		return getStudentIds();
	}

	private String getStudentIds() {
    if (studentDetails.isEmpty()) {
        try {
            File file = new File("StudentRecords.csv");
            java.util.Scanner scanner = new java.util.Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",\\s*");
                String studentDetail = String.format("Student ID: %s\nName: %s\nAge: %s\nGPA: %s",
                        parts[0], parts[1], parts[2], parts[3]);
                studentDetails.put(parts[0], studentDetail);
            }
            scanner.close();
        } catch (java.io.FileNotFoundException e) {
            return "Error: Student records file not found";
        }
    }
    
    return String.join(",", studentDetails.keySet());
}
}
