package com.pat.jp.system.modules.system.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.pat.jp.common.base.BaseEntity;
import com.pat.jp.common.utils.enums.DataScopeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 19:25
 **/
@Getter
@Setter
public class Role extends BaseEntity implements Serializable {

    @NotNull(groups = {Update.class})
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @JSONField(serialize = false)
    @ApiModelProperty(value = "用户", hidden = true)
    private Set<User> users;

    @ApiModelProperty(value = "菜单", hidden = true)
    private Set<Menu> menus;

    @ApiModelProperty(value = "部门", hidden = true)
    private Set<Dept> depts;

    @NotBlank
    @ApiModelProperty(value = "名称", hidden = true)
    private String name;

    @ApiModelProperty(value = "数据权限，全部 、 本级 、 自定义")
    private String dataScope = DataScopeEnum.THIS_LEVEL.getValue();

    @ApiModelProperty(value = "级别，数值越小，级别越大")
    private Integer level = 3;

    @ApiModelProperty(value = "描述")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
