package com.pat.jp.system.modules.security.rest;

import cn.hutool.core.util.IdUtil;
import com.pat.jp.common.annotation.rest.AnonymousGetMapping;
import com.pat.jp.common.annotation.rest.AnonymousPostMapping;
import com.pat.jp.common.utils.RedisUtil;
import com.pat.jp.system.modules.security.config.bean.LoginCodeEnum;
import com.pat.jp.system.modules.security.config.bean.LoginProperties;
import com.pat.jp.system.modules.security.config.bean.SecurityProperties;
import com.pat.jp.system.modules.security.service.dto.AuthUserDto;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    private final RedisUtil redisUtils;
    private final SecurityProperties properties;
    @Resource
    private LoginProperties loginProperties;

    @ApiOperation("登录授权")
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) {
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", "12345");
            put("user", "88888");
        }};
        return ResponseEntity.ok(authInfo);
    }

    @ApiOperation("获取验证码")
    @AnonymousGetMapping(value = "/code")
    public ResponseEntity<Object> getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};

        return ResponseEntity.ok(imgResult);
    }
}
