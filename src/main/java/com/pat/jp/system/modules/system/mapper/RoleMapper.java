package com.pat.jp.system.modules.system.mapper;

import com.pat.jp.system.modules.system.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 19:49
 **/
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 根据用户ID查询
     * @param id 用户ID
     * @return /
     */
    Set<Role> findByUserId(Long id);
}
