package com.pat.jp.hellotest.controller;

import com.pat.jp.hellotest.entity.User;
import com.pat.jp.hellotest.service.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 13:26
 **/

//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(methods = RequestMethod.POST, allowCredentials = "true") // 后端解决跨域问题
public class HelloController {

    @Resource
    Hello hello;

    @RequestMapping(value = "hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping(value = "login")
    public String login() {
        return "hello world login";
    }

    @RequestMapping(value = "queryUsers", method = {RequestMethod.POST})
    public List<User> queryList() {
        List<User> list = hello.queryUserList();
        for (User user : list) {
            System.out.println(user);
        }
        return list;
    }
}
