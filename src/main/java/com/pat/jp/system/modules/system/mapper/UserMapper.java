package com.pat.jp.system.modules.system.mapper;


import com.pat.jp.system.modules.system.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: huangwei
 * @date: 2023-07-12
 * @time: 11:04
 **/
@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return /
     */
    User findByUsername(String username);
}
