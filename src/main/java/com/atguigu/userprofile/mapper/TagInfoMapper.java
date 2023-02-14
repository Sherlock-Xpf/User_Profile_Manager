package com.atguigu.userprofile.mapper;

import com.atguigu.userprofile.bean.TagInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-13
 */
public interface TagInfoMapper extends BaseMapper<TagInfo> {

    @Select("select  tg.id,tag_code ,tag_name,tag_level ,parent_tag_id,tag_task_id,tag_comment,tg.create_time, task_status " +
            "from tag_info tg left join  task_info tk on tg.tag_task_id=tk.id  order by tag_level ,id"  )
    public List<TagInfo> getTagInfoAllWithStatus();


    @Select("select  stg.id,stg.tag_code ,stg.tag_name,stg.tag_level ,stg.parent_tag_id,stg.tag_task_id,stg.tag_comment,stg.create_time " +
            "from tag_info stg  inner join  tag_info ptg on stg.parent_tag_id=ptg.id where ptg.tag_code =#{parentTagCode}  order by stg.tag_code"  )
    public List<TagInfo> getTagValueList(String parentTagCode);
}
