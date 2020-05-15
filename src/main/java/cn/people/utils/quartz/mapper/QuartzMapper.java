package cn.people.utils.quartz.mapper;

import cn.people.utils.quartz.domain.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
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
    List<QuartzJob> findByIdIsFalse();

}
