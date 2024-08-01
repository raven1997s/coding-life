package com.raven.dream.job.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageQueryBaseReq extends AbstractBean {

    private static final long serialVersionUID = 7000214222932245038L;

    @ApiModelProperty("页码，从第一页开始")
    @NotNull(message = "页码不能为空")
    @Transient
    private Integer pageNum = 0;

    @ApiModelProperty("页大小")
    @NotNull(message = "页大小不能为空")
    @Transient
    private Integer pageSize = 20;
}