package com.pat.jp.system.modules.system.service.impl;

import com.pat.jp.common.exception.EntityNotFoundException;
import com.pat.jp.system.modules.system.domain.User;
import com.pat.jp.system.modules.system.mapper.UserMapper;
import com.pat.jp.system.modules.system.service.UserService;
import com.pat.jp.system.modules.system.service.dto.UserDto;
import com.pat.jp.system.modules.system.service.mapstruct.UserStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author: huangwei
 * @date: 2023-07-12
 * @time: 11:01
 **/
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
	private final UserStructMapper userStructMapper;

	/**
	 * 根据用户名查询
	 *
	 * @param userName /
	 * @return /
	 */
	@Override
	public UserDto findByName(String userName) {
		User user = userMapper.findByUsername(userName);
		if (user == null) {
			throw new EntityNotFoundException(User.class, "name", userName);
		} else {
			return userStructMapper.toDto(user);
		}
	}
}
