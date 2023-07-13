package com.pat.jp.system.modules.system.mapper;


import com.pat.jp.system.modules.system.domain.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 19:30
 **/
@Mapper
@Repository
public interface DeptMapper {

    /**
     * 根据角色ID 查询
     *
     * @param roleId 角色ID
     * @return /
     */
    Set<Dept> findByRoleId(Long roleId);

    /**
     * 根据 PID 查询
     *
     * @param id pid
     * @return /
     */
    List<Dept> findByPid(Long id);
}
