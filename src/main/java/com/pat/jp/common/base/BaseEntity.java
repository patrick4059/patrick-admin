package com.pat.jp.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * @author: huangwei
 * @date: 2023-07-11
 * @time: 19:12
 **/
@Getter
@Setter
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "创建人", hidden = true)
    private String createBy;

    @ApiModelProperty(value = "更新人", hidden = true)
    private String updateBy;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updateTime;

    /* 分组校验 */
    public @interface Create {}

    /* 分组校验 */
    public @interface Update {}

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }
}
