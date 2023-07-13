package com.pat.jp.system.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 18:28
 **/
@Data
public class DeptSmallDto implements Serializable {

	private Long id;

	private String name;
}
