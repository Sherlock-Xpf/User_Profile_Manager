package com.atguigu.userprofile.bean;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-27
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TaskProcessLog对象", description="")
public class TaskProcessLog implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    @ApiModelProperty(value = "任务id")
    private Long taskId;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "1 启动日志  2 运行日志")
    private String taskStage;

    @ApiModelProperty(value = "任务业务日期，一般为执行时点的前一日")
    private String taskDate;

    @ApiModelProperty(value = "1 完成, 2 失败")
    private String taskExecStatus;

    @ApiModelProperty(value = "结果日志信息")
    private String taskExecMsg;

    @ApiModelProperty(value = "yarn应用id")
    private String yarnAppId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
