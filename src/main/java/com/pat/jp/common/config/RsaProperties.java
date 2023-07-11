package com.pat.jp.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 10:10
 **/
@Data
@Component
public class RsaProperties {
    public static String privateKey;

    @Value("${rsa.private_key}")
    public void setPrivateKey(String privateKey) {
        RsaProperties.privateKey = privateKey;
    }
}
