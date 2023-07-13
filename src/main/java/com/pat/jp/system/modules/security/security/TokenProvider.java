package com.pat.jp.system.modules.security.security;

import cn.hutool.core.util.IdUtil;
import com.pat.jp.common.utils.RedisUtil;
import com.pat.jp.system.modules.security.config.bean.SecurityProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 10:55
 * <p>
 * InitializingBean 实现该接口中的afterPropertiesSet方法，
 * 用于项目启动后注册完成所有的bean后，但是还为加载@Component和@Service注解时调用，
 * 比如一个场景中需要在项目启动时将所有的商品信息加载到缓存中，这时候就需要在所有bean全部注入完成后，
 * 还未执行其他逻辑代码前进行，此时就可以实现该接口，并在afterPropertiesSet方法中缓存相关信息
 * <p>
 * 本类中使用该方法，是为了在bean注册完成后，还未创建token之前设置一些token生成的信息
 **/
@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private final SecurityProperties properties;
    private final RedisUtil redisUtils;
    public static final String AUTHORITIES_KEY = "user";
    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;

    public TokenProvider(SecurityProperties properties, RedisUtil redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    /**
     * 创建Token 设置永不过期，
     * Token 的时间有效性转到Redis 维护
     *
     * @param authentication /
     * @return /
     */
    public String createToken(Authentication authentication) {
        return jwtBuilder
                // 加入ID确保生成的 Token 都不一致
                .setId(IdUtil.simpleUUID())
                .claim(AUTHORITIES_KEY, authentication.getName())
                .setSubject(authentication.getName())
                .compact();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
        Key key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        jwtBuilder = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512);
    }
}
