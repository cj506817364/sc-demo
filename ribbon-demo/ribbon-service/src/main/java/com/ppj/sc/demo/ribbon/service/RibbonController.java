package com.ppj.sc.demo.ribbon.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pipi
 * @since 2021/8/17 19:20
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        System.out.println("接收到了一次请求调用");
        return "hello, " + name;
    }

}
