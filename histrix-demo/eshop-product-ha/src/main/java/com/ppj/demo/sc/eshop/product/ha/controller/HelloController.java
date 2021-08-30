package com.ppj.demo.sc.eshop.product.ha.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pipi
 * @since 2021/8/24 19:25
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello, " + name;
    }
}
