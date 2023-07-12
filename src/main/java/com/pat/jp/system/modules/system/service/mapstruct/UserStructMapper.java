package com.pat.jp.system.modules.system.service.mapstruct;

import com.pat.jp.common.base.BaseMapper;
import com.pat.jp.system.modules.system.domain.User;
import com.pat.jp.system.modules.system.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author: huangwei
 * @date: 2023-07-12
 * @time: 11:23
 **/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserStructMapper extends BaseMapper<UserDto, User> {
}
