package com.spring.ai.demo.controller;

import com.spring.ai.demo.model.CityList;
import com.spring.ai.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spring/ai")
public class CityController {

    @Autowired
    CityService queryService;

    @GetMapping("/cities/{state}")
    public CityList getUsers(@PathVariable String state) {
        return queryService.getCities(state);
    }

    @GetMapping("/question/{question}")
    public String getAnswer(@PathVariable String question) {
        return queryService.getAnswer(question);
    }

}
