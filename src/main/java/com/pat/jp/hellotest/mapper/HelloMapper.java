package com.pat.jp.hellotest.mapper;

import com.pat.jp.hellotest.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 17:26
 **/

@Mapper
@Repository
public interface HelloMapper {
    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
