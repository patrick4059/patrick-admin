package com.pat.jp.system.modules.system.service.mapstruct;

import com.pat.jp.common.base.BaseMapper;
import com.pat.jp.system.modules.system.domain.Role;
import com.pat.jp.system.modules.system.service.dto.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 19:55
 **/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallStructMapper extends BaseMapper<RoleSmallDto, Role> {
}
