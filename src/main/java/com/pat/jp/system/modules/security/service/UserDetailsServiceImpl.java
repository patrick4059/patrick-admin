package com.pat.jp.system.modules.security.service;

import com.pat.jp.system.modules.security.config.bean.LoginProperties;
import com.pat.jp.system.modules.system.service.UserService;
import com.pat.jp.system.modules.system.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 18:10
 **/
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final LoginProperties loginProperties;
    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询信息
        UserDto user = userService.findByName(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        return null;
    }
}
