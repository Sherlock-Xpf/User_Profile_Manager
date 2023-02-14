package com.atguigu.userprofile.service;

import com.atguigu.userprofile.bean.TagInfo;
import com.atguigu.userprofile.bean.TaskInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-15
 */
public interface TaskInfoService extends IService<TaskInfo> {
    public void saveTaskInfoWithTag( TaskInfo taskInfo);

    public TaskInfo getTaskInfoWithTag(Long taskId);




}
