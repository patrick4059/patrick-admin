package com.pat.jp.hellotest.service;

import com.pat.jp.hellotest.entity.User;

import java.util.List;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 17:27
 **/
public interface Hello {
    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
