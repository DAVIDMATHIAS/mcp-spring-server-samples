package com.example.service.document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Prompts {

    public static final String FIND_LANGUAGE = """
            
            Take a deep breath, analyze, Give me answer in one word,
            which programming language you are using based on my project structure? For Example: java, Net, python, react, angular, node, javascript, etc. In case you identify language as JavaScript, try to identify the framework or library used in the project. For Example: react, angular, vue, etc.
            In case you determine x.js for example node.js, trim the answer to node.
            """;

    public static final String FIND_FRAMEWORK = """
            Take a deep breath, analyze, Give me answer in one word. Based on the files available in the home directory like bild.gradle or package.json.
            try to identify framework used.
            Rules are:
            If language is Java, possible frameworks are Batch, Integration, Microservices or Library.
                If file content has camel, return integration.
                If file content has spring-boot-starter-batch, return batch.
                If none matches, return microservices.
            If language is JS or TS, possible frameworks are react or angular.
                If file content has react, return react.
                If file content has angular, return angular.
                If none matches, return react.
            """;

    public static final String FIND_TOPIC = """
            Find the topic using getHintToFindTopic  tool
                Run the tool with language and framework
                Use returned prompt to find topic
            """;

    public static final String GET_CONTENT = """
            This tool fetches the documentation of a given topic language and framework
            """;

    public static final String DOCUMENT_TO_FIND_TOPIC = """
            This tool returns rules to find topic, once the topic is found getContent tool has to be called using this topic
            """;


    private static final String JAVA_PROMPT = """
                   Query: Anything related to kafka
                   Respond: kafka
            
                   Query: Anything related to oauth2, Oauth2, OAUTH2, client library, JWT, opaque token, access token, refresh token, authorization code, client credentials, password grant type, spring security, spring-security, spring-security-oauth2, spring-security-oauth2-client, spring-security-oauth2-resource-server
                   Respond: oauth2
            
                   Query: Anything related to Openshift, ocp, kubernetes, k8s, docker, container, pod, deployment, service, route, image stream, build config, config map, secret, persistent volume claim, persistent volume, namespace, project, template, helm, chart, operator, custom resource definition, crd
                   Respond: openshift
            
                   Query: Anything related to spring, Spring, SPRING, spring-boot, spring-boot-starter, spring-boot-starter-web, spring-boot-starter-data-jpa, spring-boot-starter-security, spring-boot-starter-test, spring-boot-starter-actuator, spring-boot-starter-aop, spring-boot-starter-webflux, spring-boot-starter-thymeleaf, spring-ai
                   Respond: spring
            
                   Query: Anything related to java, Java, JAVA, jdk, jre, jvm, java 8, java 11, java 17, java 21, java 22, java 23, java 24, java 25, java 26, java 27, java 28, java 29
                   Respond: java
            
            """;

    private static final String REACT_PROMPT = """
                     Query: Anything related to modern webapp, webapp, web app, web application, webapp, web application, reactjs, react.js, reactjs.org, reactjs.com, reactjs.net, reactjs.io, reactjs.co.uk, reactjs.co.in, react native, react-native, react-native.org, react-native.com, react-native.net, react-native.io, react-native.co.uk, react-native.co.in,
                     Respond: react
            
                     Query: Anything related to angular, Angular, ANGULAR, angularjs, angular.js, angularjs.org, angularjs.com, angularjs.net, angularjs.io, angularjs.co.uk, angularjs.co.in
                     Respond: angular
                      Query: If anything related to micro front end, deploying micro front end, deploying mfe, mfe
                     Respond: react_mfe
            
                     Query: If anything website, plain website, personal page, company page, promotion page, landing page, flash, macromedia flash
                     Respond: simple_web
            
            
            """;


    public static Map<String, String> getPromptMap() {
        return Map.of(
                "java", JAVA_PROMPT,
                "java_batch", JAVA_PROMPT,
                "java_integration", JAVA_PROMPT,
                "java_microservices", JAVA_PROMPT,
                "react", REACT_PROMPT
        );
    }

    private static Map<String, String> documentationMap;

    public static String getDocumentation(String keyword) {
        if (documentationMap == null) {
            documentationMap = new HashMap<>();
        }
        String content = documentationMap.get(keyword);
        if(content == null) {
            String fileName = keyword + ".md";
            if (Files.exists(Paths.get(fileName))) {
                try {

                    content = Files.readString(Paths.get(fileName));
                    documentationMap.put(keyword, content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }


}