package com.pat.jp.system.modules.security.config;

import com.pat.jp.common.annotation.AnonymousAccess;
import com.pat.jp.common.utils.RequestMethodEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;


/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 18:23
 **/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	private final ApplicationContext applicationContext;

	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
		// 去除 ROLE_ 前缀
		return new GrantedAuthorityDefaults("");
	}

	/**
	 * @return
	 * @Description: 密码保存到数据库中为加密形式
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 密码加密方式
		return new BCryptPasswordEncoder();
	}

	/**
	 * @param httpSecurity
	 * @return
	 * @Description: 授权
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// 搜寻匿名标记 url： @AnonymousAccess
		RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
		Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
		// 获取匿名标记
		Map<String, Set<String>> anonymousUrls = getAnonymousUrl(handlerMethodMap);
		httpSecurity
				// 禁用 CSRF
				.csrf().disable()
				// 不通过Session获取SecurityContext
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				// 对静态资源放行，不作拦截
				.antMatchers(
						HttpMethod.GET,
						"/*.html",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js",
						"/webSocket/**"
				).permitAll()
				// swagger 文档
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/*/api-docs").permitAll()
				// 文件
				.antMatchers("/avatar/**").permitAll()
				.antMatchers("/file/**").permitAll()
				// 阿里巴巴 druid
				.antMatchers("/druid/**").permitAll()
				// 放行OPTIONS请求
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				// 自定义匿名访问所有url放行：允许匿名和带Token访问，细腻化到每个 Request 类型
				// GET
				.antMatchers(HttpMethod.GET, anonymousUrls.get(RequestMethodEnum.GET.getType()).toArray(new String[0])).permitAll()
				// POST
				.antMatchers(HttpMethod.POST, anonymousUrls.get(RequestMethodEnum.POST.getType()).toArray(new String[0])).permitAll()
				// PUT
				.antMatchers(HttpMethod.PUT, anonymousUrls.get(RequestMethodEnum.PUT.getType()).toArray(new String[0])).permitAll()
				// PATCH
				.antMatchers(HttpMethod.PATCH, anonymousUrls.get(RequestMethodEnum.PATCH.getType()).toArray(new String[0])).permitAll()
				// DELETE
				.antMatchers(HttpMethod.DELETE, anonymousUrls.get(RequestMethodEnum.DELETE.getType()).toArray(new String[0])).permitAll()
				// 所有类型的接口都放行
				.antMatchers(anonymousUrls.get(RequestMethodEnum.ALL.getType()).toArray(new String[0])).permitAll()
				// 所有请求都需要认证
				.anyRequest().authenticated();
	}

	private Map<String, Set<String>> getAnonymousUrl(Map<RequestMappingInfo, HandlerMethod> handlerMethodMap) {
		Map<String, Set<String>> anonymousUrls = new HashMap<>(6);
		Set<String> get = new HashSet<>();
		Set<String> post = new HashSet<>();
		Set<String> put = new HashSet<>();
		Set<String> patch = new HashSet<>();
		Set<String> delete = new HashSet<>();
		Set<String> all = new HashSet<>();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
			HandlerMethod handlerMethod = infoEntry.getValue();
			AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
			if (null != anonymousAccess) {
				List<RequestMethod> requestMethods = new ArrayList<>(infoEntry.getKey().getMethodsCondition().getMethods());
				RequestMethodEnum request = RequestMethodEnum.find(requestMethods.size() == 0 ? RequestMethodEnum.ALL.getType() : requestMethods.get(0).name());
				switch (Objects.requireNonNull(request)) {
					case GET:
						get.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					case POST:
						post.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					case PUT:
						put.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					case PATCH:
						patch.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					case DELETE:
						delete.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					default:
						all.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
				}
			}
		}
		anonymousUrls.put(RequestMethodEnum.GET.getType(), get);
		anonymousUrls.put(RequestMethodEnum.POST.getType(), post);
		anonymousUrls.put(RequestMethodEnum.PUT.getType(), put);
		anonymousUrls.put(RequestMethodEnum.PATCH.getType(), patch);
		anonymousUrls.put(RequestMethodEnum.DELETE.getType(), delete);
		anonymousUrls.put(RequestMethodEnum.ALL.getType(), all);
		return anonymousUrls;
	}

}
