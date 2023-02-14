package com.atguigu.userprofile.service.impl;

import com.atguigu.userprofile.bean.TaskInfo;
import com.atguigu.userprofile.bean.TaskProcess;
import com.atguigu.userprofile.constants.ConstCodes;
import com.atguigu.userprofile.mapper.TaskProcessMapper;
import com.atguigu.userprofile.service.TaskInfoService;
import com.atguigu.userprofile.service.TaskProcessService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.atguigu.userprofile.constants.ConstCodes.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-28
 */
@Service
@DS("mysql")
public class TaskProcessServiceImpl extends ServiceImpl<TaskProcessMapper, TaskProcess> implements TaskProcessService {

    @Autowired
    TaskInfoService taskInfoService;

    @Autowired
    TaskProcessMapper taskProcessMapper;


    public void updateStatus(Long taskProcessId,String status){
        updateStatus( taskProcessId, null,  status);
    }

    public void updateStatus(Long taskProcessId ,String yarnAppId,String status){
        TaskProcess taskProcess = new TaskProcess();
        taskProcess.setId(taskProcessId);
        taskProcess.setTaskExecStatus(status);
        taskProcess.setYarnAppId(yarnAppId);
        if(status.equals(TASK_EXEC_STATUS_START  )){
            taskProcess.setStartTime(new Date());
        }else if(status.equals( TASK_EXEC_STATUS_FAILED)||status.equals( TASK_EXEC_STATUS_FINISHED)){
            taskProcess.setEndTime(new Date());
        }

        updateById(taskProcess);
    }


    public void genTaskProcess(String taskDate){

        List<TaskInfo> taskInfoList = taskInfoService.list(new QueryWrapper<TaskInfo>().eq("task_status", ConstCodes.TASK_STATUS_ON));
        String batchId = UUID.randomUUID().toString();
        List<TaskProcess> taskProcessList = taskInfoList.stream().map(taskInfo ->
        {
            TaskProcess taskProcess = TaskProcess.builder()
                    .taskId(taskInfo.getId())
                    .taskName(taskInfo.getTaskName())
                    .taskExecTime(taskInfo.getTaskTime())
                    .taskExecLevel(taskInfo.getTaskExecLevel())
                    .taskBusiDate(taskDate)
                    .taskExecStatus(TASK_EXEC_STATUS_TODO)
                    .batchId(batchId)
                    .createTime(new Date()).build();
            return taskProcess;
        }).collect(Collectors.toList());

        saveBatch(taskProcessList);

    }


    public  List<TaskProcess>   getTodoTaskProcessList( String taskTime){
        return taskProcessMapper.getTodoTaskProcessList( taskTime);
    }



}
