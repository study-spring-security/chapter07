package com.example.chpater07.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello!";
    }

    @GetMapping("/caio")
    public String ciao(){
        return "Ciao!";
    }

    @GetMapping("/hola")
    public String hola(){
        return "Hola!";
    }

}
