package com.pat.jp.system.modules.security.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author: huangwei
 * @date: 2023-07-10
 * @time: 15:06
 **/
@Getter
@Setter
public class AuthUserDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";
}
