package com.raven.dream.job.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Description:
 * date: 2023/8/21 18:20
 *
 * @author raven
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IdReq extends AbstractBean {
    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private Long id;
}