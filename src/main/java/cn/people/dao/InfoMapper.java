package cn.people.dao;

import cn.people.domain.Info;
import cn.people.domain.vo.InfoVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/23  下午3:18
 * @description:
 */
public interface InfoMapper {


    /**
     * 新增信息
     * @param info
     */
    @Insert("insert into info(id,context,date,end_date,user_id,status) values(" +
            "#{id},#{context},#{date},#{endDate},#{user_id},#{status})")
    void increaseInfo(Info info);

    /**
     * 删除info集user_m_info中的信息
     * @param id
     */
    @Delete("DELETE INTO info WHERE id = #{id} AND DELETE INTO user_m_info WHERE info_id = #{id}")
    void deleteInfo(String id);

    /**
     * 更新info信息
     * @param info
     */
    @Update("update info set date=#{date},end_date=#{endDate},context=#{context}")
    void updateInfo(InfoVO info);

    /**
     * 获取信息概况
     * @return
     */
    @Select("SELECT * FROM info ")
    List<InfoVO> selectRoughlyInfo(String id);
}
