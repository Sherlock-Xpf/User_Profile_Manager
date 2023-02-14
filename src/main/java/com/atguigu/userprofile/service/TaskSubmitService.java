package com.atguigu.userprofile.service;

import com.atguigu.userprofile.bean.TaskProcess;

public interface TaskSubmitService {


    public void submitTask(TaskProcess taskProcess, boolean isRetry);
}
