package com.atguigu.userprofile.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagTreeNode {
    String id ;
    String tagName;
    String taskId;
    String tagCode;
    String parentTagId;
    String tagLevel;
    String taskStatus;
    List<TagTreeNode> children;

    public TagTreeNode(TagInfo tagInfo){
        this.id=tagInfo.getId().toString();
        this.tagName=tagInfo.getTagName();
        this.tagCode=tagInfo.getTagCode();
        this.tagLevel=tagInfo.getTagLevel()!=null?tagInfo.getTagLevel().toString():null;
        this.parentTagId=tagInfo.getParentTagId()!=null?tagInfo.getParentTagId().toString():null;
        this.taskId=tagInfo.getTagTaskId()!=null?tagInfo.getTagTaskId().toString():null;
        this.taskStatus=tagInfo.getTaskStatus()!=null?tagInfo.getTaskStatus():null;
        children=new ArrayList<>();
    }

}
