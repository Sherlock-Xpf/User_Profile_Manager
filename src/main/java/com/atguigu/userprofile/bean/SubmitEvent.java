package com.atguigu.userprofile.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SubmitEvent {

   Long taskProcessId;

   Long taskId;

    String appName;

    String jarFilePath;

    String mainClass;

    String master;

    String deployMode;

    Map<String,String> sparkArgs;

    Map<String,String> sparkConf;

    List<String> appArgs;


}
