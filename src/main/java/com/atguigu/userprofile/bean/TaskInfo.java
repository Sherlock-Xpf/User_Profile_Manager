package com.atguigu.userprofile.bean;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minidev.json.JSONObject;

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
@ApiModel(value="TaskInfo对象", description="")
public class TaskInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务状态")
    private String taskStatus;

    @ApiModelProperty(value = "任务说明")
    private String taskComment;

    @ApiModelProperty(value = "任务作业时间(小时分)")
    private String taskTime;

    @ApiModelProperty(value = "任务类型(标签,流程)")
    private String taskType;

    @ApiModelProperty(value = "执行方式(spark,sparksql)")
    private String execType;

    @ApiModelProperty(value = "启动执行的主类")
    private String mainClass;

    @ApiModelProperty(value = "程序jar文件id")
    private Long fileId;

    @ApiModelProperty(value = "启动任务的参数")
    private String taskArgs;

    @ApiModelProperty(value = "启动的执行的sql")
    private String taskSql;

    @ApiModelProperty(value = "执行层级")
    private Long taskExecLevel;

    @TableField(exist = false)
    private List<TaskTagRule> taskTagRuleList;

    @TableField(exist = false)
    private Long tagId;

    @TableField(exist = false)
    private String fileName;

    @TableField(exist = false)
    private String filePath;

}
