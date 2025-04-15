package com.example.service;

import com.example.service.document.DocumentationTools;
import com.example.service.tools.Tools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ServiceApplication {
	public static final String FOLDER="/home/davidmathias/work/mcp/mcp-client-demo-01/user-context";
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	ToolCallbackProvider toolCallbackProvider(Tools tools, DocumentationTools documentationTools){
		return MethodToolCallbackProvider.builder().toolObjects(tools, documentationTools).build();
	}
}


