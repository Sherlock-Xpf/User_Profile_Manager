package com.atguigu.userprofile.bean;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TagCommonTask对象", description="")
public class TagCommonTask implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private Long taskFileId;

    private String mainClass;

    private Date updateTime;

    @TableField(exist = false)
    private FileInfo fileInfo;

}
