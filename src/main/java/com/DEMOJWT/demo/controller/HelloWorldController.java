package com.DEMOJWT.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("hello")
    public String helloWorld(@RequestParam(value="name", defaultValue="Usuario") String name) {
        return "Bienvenido al sistema "+name+"!!";
    }
}
