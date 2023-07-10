package com.pat.jp.system.modules.security.rest;

import com.pat.jp.common.annotation.rest.AnonymousPostMapping;
import com.pat.jp.system.modules.security.service.dto.AuthUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: huangwei
 * @date: 2023-07-10
 * @time: 14:35
 **/
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor // 使用该注解不用频繁使用@Autowired注解，只需要将对象声明成final即可，或者不使用final，使用@notnull注解也可以
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {
    @ApiOperation("登录授权")
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) {
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", "12345");
            put("user", "88888");
        }};
        return ResponseEntity.ok(authInfo);
    }
}
