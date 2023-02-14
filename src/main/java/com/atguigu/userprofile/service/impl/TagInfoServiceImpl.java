package com.atguigu.userprofile.service.impl;

import com.atguigu.userprofile.bean.TagInfo;
import com.atguigu.userprofile.mapper.TagInfoMapper;
import com.atguigu.userprofile.service.TagInfoService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-13
 */
@Service
@DS("mysql")
public class TagInfoServiceImpl extends ServiceImpl<TagInfoMapper, TagInfo> implements TagInfoService {

    @Autowired
    TagInfoMapper tagInfoMapper;

    public List<TagInfo> getTagInfoAllWithStatus(){

       return   tagInfoMapper.getTagInfoAllWithStatus();
    }

    public TagInfo getTagInfo(Long taskId){
        TagInfo tagInfo =  getById(taskId);
        if(tagInfo.getTagLevel()>1L){
            TagInfo parentTagInfo = getById(tagInfo.getParentTagId());
            tagInfo.setParentTagLevel(parentTagInfo.getTagLevel());
            tagInfo.setParentTagName(parentTagInfo.getTagName());
            tagInfo.setParentTagCode(parentTagInfo.getTagCode());
        }
        return tagInfo;
    }

       public List<TagInfo> getTagValueList(String parentTagCode){
         return tagInfoMapper.getTagValueList(parentTagCode);
       }

       public Map<String,TagInfo> getTagInfoMapWithCode(){
           List<TagInfo> tagInfoList =  list();
           Map<String,TagInfo> tagInfoMap=new HashMap<>();
           for (TagInfo tagInfo : tagInfoList) {
               tagInfoMap.put(tagInfo.getTagCode(),tagInfo);
           }
           return tagInfoMap;

       }

}
