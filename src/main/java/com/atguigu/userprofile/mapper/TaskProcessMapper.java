package com.atguigu.userprofile.mapper;

import com.atguigu.userprofile.bean.TaskProcess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangchen
 * @since 2021-04-28
 */
public interface TaskProcessMapper extends BaseMapper<TaskProcess> {

    @Select(" SELECT * FROM task_process  tp " +
            "INNER JOIN  (\n" +
            "\t SELECT batch_id,MIN(task_exec_level) todo_level FROM (\n" +
            "\t     SELECT batch_id,task_exec_level , IF( SUM( IF( task_exec_status IN ('TODO' ) ,1,0))>0,1,0)  has_todo FROM task_process GROUP BY batch_id, task_exec_level \n" +
            "\t )tl WHERE   has_todo=1\n" +
            "\tGROUP BY batch_id\n" +
            " ) min_level ON tp.batch_id=min_level.batch_id AND  tp.task_exec_level=todo_level\n" +
            " WHERE tp.batch_id NOT IN (\n" +
            "    SELECT batch_id  FROM task_process  WHERE task_exec_status IN ('START','SUBMITTED' ,'RUNNING' )\n" +
            " )\n" +
            " AND task_exec_time<= #{curTime} AND task_exec_status ='TODO'")
    public List<TaskProcess > getTodoTaskProcessList( @Param("curTime") String curTime ) ;

}
