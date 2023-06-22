package com.pat.jp.hellotest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 13:26
 **/

@RestController
public class HelloTest {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello world";
    }
}
