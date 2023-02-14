package com.atguigu.userprofile.controller;


import com.alibaba.fastjson.JSON;
import com.atguigu.userprofile.bean.TagOption;
import com.atguigu.userprofile.bean.TagInfo;
import com.atguigu.userprofile.bean.TagTreeNode;
import com.atguigu.userprofile.service.FileInfoService;
import com.atguigu.userprofile.service.TagInfoService;
import com.atguigu.userprofile.service.TaskInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-13
 */
@RestController
public class TagInfoController {
    @Autowired
    TagInfoService tagInfoService;

    @Autowired
    TaskInfoService taskInfoService;

    @Autowired
    FileInfoService fileInfoService;

    @GetMapping("/taginfo/{tagId}")
    @CrossOrigin
    public String getTagInfo(@PathVariable("tagId") Long tagId){
        TagInfo tagInfo = tagInfoService.getTagInfo(tagId);
        return  JSON.toJSONString(tagInfo) ;
    }

    @PostMapping("taginfo")
    @CrossOrigin
    public void saveTagInfo(@RequestBody TagInfo tagInfo){
        if(tagInfo.getId()==null){
            tagInfo.setCreateTime(new Date());
        }
        tagInfoService.saveOrUpdate(tagInfo);
    }

    @RequestMapping("taglist")
    @CrossOrigin
    public String tagInfoList(){

        List<TagInfo> tagInfoList = tagInfoService.getTagInfoAllWithStatus();
        List<TagTreeNode> tagTreeNodeList = tagInfoList.stream().map(tagInfo -> new TagTreeNode(tagInfo)).collect(Collectors.toList());

        Map<String ,TagTreeNode> tagTreeNodeMap = new HashMap();
        List<TagTreeNode> headTreeNodeList=new ArrayList<>();

        //采用map缓存策略  相比双层for 和 递归  ，循环次数少，不容易栈溢出，但是需要数据层级有序 （子辈必须在父辈后面）
        for (TagTreeNode tagTreeNode : tagTreeNodeList) {
            if(tagTreeNode.getParentTagId()==null){
                headTreeNodeList.add(tagTreeNode);
            }else{
                //加入长辈的孩子集合
                TagTreeNode parentTagTreeNode = tagTreeNodeMap.get(tagTreeNode.getParentTagId());
                List<TagTreeNode> broTagTreeNodeList = parentTagTreeNode.getChildren();
                broTagTreeNodeList.add(tagTreeNode);
            }
            //把自己加入集合
            tagTreeNodeMap.put(tagTreeNode.getId(),tagTreeNode);
        }
        String tagJson = JSON.toJSONString(headTreeNodeList);
        System.out.println(tagJson);

        return tagJson;
    }


    @RequestMapping("subtags/{parentTagId}")
    @CrossOrigin
    public String tagInfoList(@PathVariable("parentTagId") String parentTagId){

        List<TagInfo> subTagInfoList = tagInfoService.list(new QueryWrapper<TagInfo>().eq("parent_tag_id", Long.valueOf(parentTagId)));

        String tagJson = JSON.toJSONString(subTagInfoList);
        System.out.println(tagJson);

        return tagJson;
    }

    @RequestMapping("/tag-cascader/{level}")
    @CrossOrigin
    public String tagsCascader(@PathVariable("level") Long level){
        List<TagInfo> tagInfoList = tagInfoService.list(new QueryWrapper<TagInfo>().le("tag_level",level).orderByAsc("tag_level"));
        List<TagOption> tagOptionList = tagInfoList.stream().map(tagInfo -> new TagOption(tagInfo)).collect(Collectors.toList());

        Map<Long , TagOption> tagOptionMap = new HashMap();
        List<TagOption> headTagOptionList =new ArrayList<>();

        //采用map缓存策略  相比双层for 和 递归  ，循环次数少，不容易栈溢出，但是需要数据层级有序 （子辈必须在父辈后面）
        for (TagOption tagOption : tagOptionList) {
            if(tagOption.getParentTagId()==null){
                headTagOptionList.add(tagOption);
            }else{
                //加入长辈的孩子集合
                TagOption parentTagOption = tagOptionMap.get(tagOption.getParentTagId());
                if(parentTagOption.getChildren()==null){
                    parentTagOption.setChildren(new ArrayList<>());
                }
                List<TagOption> broTagOptionList = parentTagOption.getChildren();
                broTagOptionList.add(tagOption);
            }
            //把自己加入集合
            tagOptionMap.put(tagOption.getTagId(), tagOption);
        }
        String tagJson = JSON.toJSONString(headTagOptionList);
        System.out.println(tagJson);

        return tagJson;

    }

    @RequestMapping("/tag-value-list/{parentTagCode}")
    @CrossOrigin
    public String getTagValueList(@PathVariable("parentTagCode") String parentTagCode){
        List<TagInfo> tagValueList = tagInfoService.getTagValueList(parentTagCode);
        List<TagOption> tagOptionList = tagValueList.stream().map(tagInfo -> new TagOption(tagInfo)).collect(Collectors.toList());

        return JSON.toJSONString(tagOptionList);
    }

    @DeleteMapping("/taginfo/{id}")
    @CrossOrigin
    public String deleteTagInfo(@PathVariable("id") Long tagId){
        tagInfoService.removeById(tagId);
        return "success";
    }

}

