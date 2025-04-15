package com.example.service.document;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class DocumentationTools {

    @Tool(description = Prompts.GET_CONTENT)
    public String getContent(@ToolParam(description = Prompts.FIND_LANGUAGE) String langauge, @ToolParam(description = Prompts.FIND_FRAMEWORK)String framework, @ToolParam(description = Prompts.FIND_TOPIC)String topic){
        System.out.printf("Looking for documentation using %s, %s %s %n",langauge,framework,topic);
        String documentation = Prompts.getDocumentation(topic);
        System.out.println("Documentation found: " + documentation);
        return documentation;
    }

    @Tool(description = Prompts.DOCUMENT_TO_FIND_TOPIC)
    public String getHintToFindTopic(@ToolParam(description = Prompts.FIND_LANGUAGE) String langauge, @ToolParam(description = Prompts.FIND_FRAMEWORK)String framework){
        System.out.printf("Looking for prompt using %s, %s %n",langauge,framework);
        String prompt = null;
        if(langauge == null || langauge.isEmpty()){
            return "Please provide a valid language.";
        }
        if(framework == null || framework.isEmpty()){
            prompt =  Prompts.getPromptMap().get(langauge);
        }else{
             prompt = Prompts.getPromptMap().get(langauge + "_" + framework);
            if(prompt == null){
                return Prompts.getPromptMap().get(langauge);
            }

        }

        System.out.println("Returning prompt: " + prompt);
        return prompt;
    }
}
