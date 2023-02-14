package com.atguigu.userprofile.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TaskTagRule对象", description="")
public class TaskTagRule implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标签主键")
    private Long tagId;

    @ApiModelProperty(value = "任务id")
    private Long taskId;

    @ApiModelProperty(value = "查询值")
    private String queryValue;

    @ApiModelProperty(value = "对应子标签id")
    private Long subTagId;


}
