package com.atguigu.userprofile.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TaskProcess对象", description="")
public class TaskProcess implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务id")
    private Long taskId;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务触发时间")
    private String taskExecTime;

    @ApiModelProperty(value = "任务执行日期")
    private String taskBusiDate;

    @ApiModelProperty(value = "任务状态 TODO ,START,SUBMITTED,RUNNING,FAILED,FINISHED")
    private String taskExecStatus;

    @ApiModelProperty(value = "任务执行层级")
    private Long taskExecLevel;

    @ApiModelProperty(value = "yarn的application_id")
    private String yarnAppId;

    @ApiModelProperty(value = "批次编号")
    private String batchId;


    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "启动时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间(包括完成和失败)")
    private Date endTime;


}
