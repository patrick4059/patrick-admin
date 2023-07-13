package com.pat.jp.system.modules.system.service;

import com.pat.jp.system.modules.system.service.dto.RoleSmallDto;
import com.pat.jp.system.modules.system.service.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 19:03
 **/
public interface RoleService {

    /**
     * 根据用户ID查询
     *
     * @param id 用户ID
     * @return /
     */
    List<RoleSmallDto> findByUsersId(Long id);

    /**
     * 获取用户权限信息
     *
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDto user);
}
