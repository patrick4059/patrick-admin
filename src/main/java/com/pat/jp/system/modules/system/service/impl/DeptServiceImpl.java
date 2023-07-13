package com.pat.jp.system.modules.system.service.impl;

import com.pat.jp.system.modules.system.domain.Dept;
import com.pat.jp.system.modules.system.mapper.DeptMapper;
import com.pat.jp.system.modules.system.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 19:28
 **/
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dept")
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    /**
     * 根据角色ID查询
     *
     * @param id /
     * @return /
     */
    @Override
    public Set<Dept> findByRoleId(Long id) {
        return deptMapper.findByRoleId(id);
    }

    /**
     * 根据PID查询
     *
     * @param pid /
     * @return /
     */
    @Override
    public List<Dept> findByPid(long pid) {
        return deptMapper.findByPid(pid);
    }

    /**
     * 获取
     *
     * @param deptList
     * @return
     */
    @Override
    public List<Long> getDeptChildren(List<Dept> deptList) {
        List<Long> list = new ArrayList<>();
        deptList.forEach(dept -> {
                    if (dept!=null && dept.getEnabled()) {
                        List<Dept> depts = deptMapper.findByPid(dept.getId());
                        if (depts.size() != 0) {
                            list.addAll(getDeptChildren(depts));
                        }
                        list.add(dept.getId());
                    }
                }
        );
        return list;
    }
}
