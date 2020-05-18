package cn.people.dao;

import cn.people.utils.quartz.domain.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/13  下午9:40
 * @description:
 */
@Mapper
public interface QuartzMapper {

    @Select("select * from scheduler")
    @Results({
            @Result(property = "jobName",column = "job_name"),
            @Result(property = "methodName",column = "method_name"),
    })
    List<QuartzJob> findByIdIsFalse();

}
