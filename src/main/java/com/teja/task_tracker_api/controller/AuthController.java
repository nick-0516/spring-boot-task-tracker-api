package com.teja.task_tracker_api.controller;


import com.teja.task_tracker_api.dto.AuthRequest;
import com.teja.task_tracker_api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        if ("ravi".equals(authRequest.getUsername()) && "pass".equals(authRequest.getPassword())) {
            return jwtUtil.generateToken(authRequest.getUsername());
        } else {
            throw new Exception("Invalid username/password");
        }
    }
}
