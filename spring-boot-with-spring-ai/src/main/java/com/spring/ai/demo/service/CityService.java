package com.spring.ai.demo.service;

import com.spring.ai.demo.model.CityList;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CityService {

    @Autowired
    ChatClient chatClient;

    public CityList getCities(String state){
        BeanOutputParser<CityList> parser = new BeanOutputParser<>(CityList.class);
        String promptString = "List of cities from {state} {format}";

        PromptTemplate template = new PromptTemplate(promptString);
//        template.add("state", state);
//        template.add("format", parser.getFormat());
//        template.setOutputParser(parser);
//        Prompt prompt = template.create();

        Map model = Map.of("state",state,"formate",parser.getFormat());
//        template.render(model);
        ChatResponse response = chatClient.call(template.create(model));
        String strResponse = response.getResult().getOutput().getContent();
       return parser.parse(strResponse);
    }
    public String getAnswer(String question){
       return chatClient.call(question);
    }


}
