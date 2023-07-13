package com.pat.jp.system.modules.system.service;

import com.pat.jp.system.modules.system.domain.Dept;

import java.util.List;
import java.util.Set;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 19:04
 **/
public interface DeptService {

    /**
     * 根据角色ID查询
     *
     * @param id /
     * @return /
     */
    Set<Dept> findByRoleId(Long id);

    /**
     * 根据PID查询
     *
     * @param pid /
     * @return /
     */
    List<Dept> findByPid(long pid);

    /**
     * 获取
     *
     * @param deptList
     * @return
     */
    List<Long> getDeptChildren(List<Dept> deptList);
}
