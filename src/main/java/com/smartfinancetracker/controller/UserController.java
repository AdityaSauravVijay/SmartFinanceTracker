package com.smartfinancetracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/greet")
    public String GreetMessage() {
        return "Hello World";
    }
}
