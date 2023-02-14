package com.atguigu.userprofile.service;

import com.atguigu.userprofile.bean.TagInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-13
 */
public interface TagInfoService extends IService<TagInfo> {

    public List<TagInfo> getTagInfoAllWithStatus();

    public TagInfo getTagInfo(Long taskId);

    public List<TagInfo> getTagValueList(String parentTagCode);

    public Map<String,TagInfo> getTagInfoMapWithCode();

}
