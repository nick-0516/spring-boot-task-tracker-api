package com.teja.task_tracker_api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name ="BearerAuth")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }

    @PostMapping("/hello")
    public String handlePost(@RequestBody String name) {
        return "You posted: " + name;
    }
}

