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
	private Util util = new Util();
    @Tool(description = "List full details of a student")
    String studentDetails(@ToolParam(description = "Student ID") String studentId) {
		return util.getStudentDetails(studentId);
	}


	@Tool(description = "List all student Ids")
	String listStudentIds() {
		return util.getStudentIds();
	}


}
