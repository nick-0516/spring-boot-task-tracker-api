package com.teja.task_tracker_api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public Map<String, String> welcome() {
        Map<String, String> response = new LinkedHashMap<>();  //using LinkedHashMAp to maintain insertion order.
        response.put("message", "Welcome to the Task Tracker API");
        response.put("docs", "/swagger-ui/index.html");
        response.put("authInstructions", "POST /api/authenticate with username='user', password='pass' to get a token");
        return response;
    }
}
