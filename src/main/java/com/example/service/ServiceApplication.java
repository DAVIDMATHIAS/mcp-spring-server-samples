package com.example.service;

import io.modelcontextprotocol.server.McpServerFeatures;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;


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
