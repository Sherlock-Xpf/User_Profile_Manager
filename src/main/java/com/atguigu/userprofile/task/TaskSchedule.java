package com.atguigu.userprofile.task;


import com.alibaba.fastjson.JSON;
import com.atguigu.userprofile.bean.TaskInfo;
import com.atguigu.userprofile.bean.TaskProcess;
import com.atguigu.userprofile.service.TaskInfoService;
import com.atguigu.userprofile.service.TaskProcessService;
import com.atguigu.userprofile.service.TaskSubmitService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import static com.atguigu.userprofile.constants.ConstCodes.TASK_STATUS_ON;


@Component
public class TaskSchedule {



  @Autowired
  TaskSubmitService taskSubmitService;

  @Autowired
  TaskProcessService taskProcessService;

  @Scheduled(cron = "0/15 * * * * ?")
  public  void executeTask(){

    //取当前日期前一天的
    Date busiDate = DateUtils.addDays(new Date(), -1);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String dataTimeStr = dateFormat.format(new Date());
    String[] dataTimeArr = dataTimeStr.split(" ");
    String curDate = dataTimeArr[0];
    String curTime = dataTimeArr[1];


    List<TaskProcess> taskProcessList = taskProcessService.getTodoTaskProcessList(curTime);


    System.out.println("获得任务列表："+ JSON.toJSONString(taskProcessList));
    for (TaskProcess taskProcess : taskProcessList) {
      taskSubmitService.submitTask(taskProcess,false);
     }


  }


}
