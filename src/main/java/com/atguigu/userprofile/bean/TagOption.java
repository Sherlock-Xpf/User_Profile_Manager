package com.atguigu.userprofile.bean;

import lombok.Data;

import java.util.List;

@Data
public class TagOption {

    Long tagId;

    Long parentTagId;

    String label;

    String value;

    List<TagOption> children;

    public TagOption(TagInfo tagInfo){
         this.tagId = tagInfo.getId();
        this.parentTagId = tagInfo.getParentTagId();
        this.label = tagInfo.getTagName();
        this.value = tagInfo.getTagCode();
       // this.children= new ArrayList<>();
    }

}
