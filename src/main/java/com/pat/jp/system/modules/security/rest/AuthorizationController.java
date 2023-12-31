package com.pat.jp.system.modules.security.rest;

import cn.hutool.core.util.IdUtil;
import com.pat.jp.common.annotation.rest.AnonymousGetMapping;
import com.pat.jp.common.annotation.rest.AnonymousPostMapping;
import com.pat.jp.common.config.RsaProperties;
import com.pat.jp.common.exception.BadRequestException;
import com.pat.jp.common.utils.RedisUtil;
import com.pat.jp.common.utils.RsaUtils;
import com.pat.jp.common.utils.StringUtils;
import com.pat.jp.system.modules.security.config.bean.LoginCodeEnum;
import com.pat.jp.system.modules.security.config.bean.LoginProperties;
import com.pat.jp.system.modules.security.config.bean.SecurityProperties;
import com.pat.jp.system.modules.security.security.TokenProvider;
import com.pat.jp.system.modules.security.service.OnlineUserService;
import com.pat.jp.system.modules.security.service.dto.AuthUserDto;
import com.pat.jp.system.modules.security.service.dto.JwtUserDto;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final OnlineUserService onlineUserService;

    @Resource
    private LoginProperties loginProperties;

    @ApiOperation("登录授权")
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // 查询验证码
        String code = (String) redisUtils.get(authUser.getUuid());
        // 清除验证码
        redisUtils.del(authUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }
        // spring Security使用参照： https://blog.csdn.net/manqishizhizhu/article/details/124201084
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        // 保存在线信息
        onlineUserService.save(jwtUserDto, token, request);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};
        if (loginProperties.isSingleLogin()) {
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }
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
