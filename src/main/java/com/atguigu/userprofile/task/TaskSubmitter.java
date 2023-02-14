package com.atguigu.userprofile.task;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.userprofile.bean.TaskInfo;
import com.atguigu.userprofile.bean.TaskProcess;
import com.atguigu.userprofile.constants.ConstCodes;
import com.atguigu.userprofile.service.TaskSubmitService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class TaskSubmitter implements Runnable {

    TaskProcess taskProcess;

    @Autowired
    private TaskSubmitService taskSubmitService;

    public TaskSubmitter(TaskProcess taskProcess){
        this.taskProcess=taskProcess;
    }

    public  void submit(){
        taskSubmitService.submitTask(taskProcess,false);
    }

    public void run(){
        submit();
    }

}
