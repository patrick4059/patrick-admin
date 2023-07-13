package com.pat.jp.system.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 18:26
 **/
@Data
public class RoleSmallDto implements Serializable {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;

}
