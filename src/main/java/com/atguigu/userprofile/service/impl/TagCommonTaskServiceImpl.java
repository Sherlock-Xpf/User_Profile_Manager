package com.atguigu.userprofile.service.impl;

import com.atguigu.userprofile.bean.FileInfo;
import com.atguigu.userprofile.bean.TagCommonTask;
import com.atguigu.userprofile.mapper.TagCommonTaskMapper;
import com.atguigu.userprofile.service.FileInfoService;
import com.atguigu.userprofile.service.TagCommonTaskService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-27
 */
@Service
@DS("mysql")
public class TagCommonTaskServiceImpl extends ServiceImpl<TagCommonTaskMapper, TagCommonTask> implements TagCommonTaskService {

    @Autowired
    FileInfoService fileInfoService;

    public  TagCommonTask getTagCommonTaskWithJarFile(Long id){
        TagCommonTask tagCommonTask =   getById(id);
        if(tagCommonTask!=null){
            FileInfo fileInfo = fileInfoService.getById(tagCommonTask.getTaskFileId());
            tagCommonTask.setFileInfo(fileInfo);
        }

        return  tagCommonTask;
    }


}
