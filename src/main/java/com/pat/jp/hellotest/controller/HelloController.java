package com.pat.jp.hellotest.controller;

import com.pat.jp.hellotest.entity.User;
import com.pat.jp.hellotest.service.Hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 13:26
 **/

@RestController
public class HelloController {

    @Resource
    Hello hello;

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/queryUsers")
    public List<User> queryList() {
        List<User> list = hello.queryUserList();
        for (User user : list) {
            System.out.println(user);
        }
        return list;
    }
}
