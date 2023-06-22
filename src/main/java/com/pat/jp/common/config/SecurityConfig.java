package com.pat.jp.common.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 18:23
 **/
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * @param http
     * @return
     * @Description: 授权
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("").hasRole("")
//                .antMatchers("").hasRole("")
//                .and()
//                .formLogin();
    }

    /**
     * @param auth
     * @return
     * @Description: 认证
     * 密码编码：passwordEncoder
     * 在springsecurity5.x后新增了很多加密方法
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

        // 这些数据正常应该从数据库取
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("root").password(new BCryptPasswordEncoder().encode("1234556")).roles("role1", "role2")
//                .and()
//                .withUser("tom").password(new BCryptPasswordEncoder().encode("123321")).roles("role1");
    }
}
