package com.webapp.tp2_lab_javareact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping
    public String home() {
        return "HOME!";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello world!";
    }

    @GetMapping("/bye")
    public String sayBye() {
        return "Goodbye World!";
    }


}
