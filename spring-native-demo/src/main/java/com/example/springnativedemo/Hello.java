package com.example.springnativedemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/")
    public String greet() {
        return "안녕하세요. 스프링네이티브 데모입니다.";
    }
}
