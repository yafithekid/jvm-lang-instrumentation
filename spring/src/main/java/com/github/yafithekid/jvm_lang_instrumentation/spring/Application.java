package com.github.yafithekid.jvm_lang_instrumentation.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

    @RequestMapping("/")
    public String hello() throws InterruptedException {
        Thread.sleep(100);
        return "executed in 100 ms";
    }
}
