package com.yash.taskmanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    String sayHello(){
        return "hello Yash";
    }
}
