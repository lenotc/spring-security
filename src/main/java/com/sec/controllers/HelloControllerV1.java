package com.sec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloControllerV1 {
    @GetMapping("/home")
    public String home() {
        return "home.html";
    }
}
