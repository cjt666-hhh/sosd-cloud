package com.sosd.cloud.controller.demo;

import com.sosd.cloud.log.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogDemo {

    @Log
    @GetMapping("/hello")
    String hello(){
        return "hello";
    }
    @Log
    @GetMapping("/aaa/{a}")
    int hello(@PathVariable(value = "a")int a ){
        return a;
    }


}
