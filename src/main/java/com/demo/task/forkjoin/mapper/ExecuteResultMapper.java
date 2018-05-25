package com.demo.task.forkjoin.mapper;

import com.demo.task.forkjoin.entity.ExecuteResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 执行结果输出
 */
@Mapper
public interface ExecuteResultMapper {
	int insert(ExecuteResult record);

	int insertSelective(ExecuteResult record);

	ExecuteResult selectByPrimaryKey(Long resultId);

	int updateByPrimaryKeySelective(ExecuteResult record);

	int updateByPrimaryKey(ExecuteResult record);

	@Select("SELECT * FROM fj_execute_result WHERE once_millis = #{onceMilli} order by task_count asc")
	List<ExecuteResult> selectByOnceMilli(int onceMilli);
}