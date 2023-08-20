package com.angel.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class HelloWorldController {

    @GetMapping(value="/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }
    
}