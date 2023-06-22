package com.pat.jp.hellotest.service.impl;

import com.pat.jp.hellotest.entity.User;
import com.pat.jp.hellotest.mapper.HelloMapper;
import com.pat.jp.hellotest.service.Hello;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 17:25
 **/

@Service
public class HelloServiceImpl implements Hello {
    @Resource
    HelloMapper helloMapper;

    @Override
    public List<User> queryUserList() {
        return helloMapper.queryUserList();
    }

    @Override
    public User queryUserById(int id) {
        return helloMapper.queryUserById(id);
    }

    @Override
    public int addUser(User user) {
        return helloMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return helloMapper.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return helloMapper.deleteUser(id);
    }
}
