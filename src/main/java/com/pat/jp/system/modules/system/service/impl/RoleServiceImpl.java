package com.pat.jp.system.modules.system.service.impl;

import com.pat.jp.common.utils.StringUtils;
import com.pat.jp.system.modules.system.domain.Menu;
import com.pat.jp.system.modules.system.domain.Role;
import com.pat.jp.system.modules.system.mapper.RoleMapper;
import com.pat.jp.system.modules.system.service.RoleService;
import com.pat.jp.system.modules.system.service.dto.RoleSmallDto;
import com.pat.jp.system.modules.system.service.dto.UserDto;
import com.pat.jp.system.modules.system.service.mapstruct.RoleSmallStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 19:41
 **/
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {
    private final RoleSmallStructMapper roleSmallStructMapper;
    private final RoleMapper roleMapper;

    /**
     * 根据用户ID查询
     *
     * @param id 用户ID
     * @return /
     */
    @Override
    public List<RoleSmallDto> findByUsersId(Long id) {
        return roleSmallStructMapper.toDto(new ArrayList<>(roleMapper.findByUserId(id)));
    }

    /**
     * 获取用户权限信息
     *
     * @param user 用户信息
     * @return 权限信息
     */
    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        Set<Role> roles = roleMapper.findByUserId(user.getId());
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .map(Menu::getPermission).collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
