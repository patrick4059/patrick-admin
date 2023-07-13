package com.pat.jp.system.modules.system.service;

import com.pat.jp.system.modules.system.service.dto.UserDto;

import java.util.List;

/**
 * @Description: 数据权限服务类
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 18:46
 **/
public interface DataService {

    /**
     * 获取数据权限
     * @param user /
     * @return /
     */
    List<Long> getDeptIds(UserDto user);

}
