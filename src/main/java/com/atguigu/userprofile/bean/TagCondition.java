package com.atguigu.userprofile.bean;

import lombok.Data;

import java.util.List;

@Data
public class TagCondition {

     String tagCode;
     String tagName;
     String operatorName;
     String operator;
     List<String> tagValues;
     List<String> tagCodePath;


}
