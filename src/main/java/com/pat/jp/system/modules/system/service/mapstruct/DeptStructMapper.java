package com.pat.jp.system.modules.system.service.mapstruct;

import com.pat.jp.common.base.BaseMapper;
import com.pat.jp.system.modules.system.domain.Dept;
import com.pat.jp.system.modules.system.service.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author: huangwei
 * @date: 2023-07-12
 * @time: 11:30
 **/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptStructMapper extends BaseMapper<DeptDto, Dept> {
}
