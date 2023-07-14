package com.pat.jp.system.modules.security.security;

import com.pat.jp.system.modules.security.config.bean.SecurityProperties;
import com.pat.jp.system.modules.security.service.OnlineUserService;
import com.pat.jp.system.modules.security.service.UserCacheClean;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: huangwei
 * @date: 2023-07-14
 * @time: 12:33
 **/
@RequiredArgsConstructor
public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	private final TokenProvider tokenProvider;
	private final SecurityProperties properties;
	private final OnlineUserService onlineUserService;
	private final UserCacheClean userCacheClean;

	@Override
	public void configure(HttpSecurity http) {
		TokenFilter customFilter = new TokenFilter(tokenProvider, properties, onlineUserService, userCacheClean);
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
