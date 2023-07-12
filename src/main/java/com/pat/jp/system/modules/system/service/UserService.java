package com.pat.jp.system.modules.system.service;


import com.pat.jp.system.modules.system.service.dto.UserDto;

/**
 * @author: huangwei
 * @date: 2023-07-12
 * @time: 10:59
 **/
public interface UserService {

    /**
     * 根据用户名查询
     *
     * @param userName /
     * @return /
     */
    UserDto findByName(String userName);
}
