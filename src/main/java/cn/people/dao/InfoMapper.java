package cn.people.dao;

import cn.people.domain.Info;
import cn.people.domain.vo.InfoVO;
import org.apache.ibatis.annotations.*;

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

    /**
     * 获取详细信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM info WHERE id=#{id} ")
    Info selectInfo(String id);

    /**
     * 统计user_m_info中用户已读信息
     * @param uid
     * @return
     */
    @Select("SELECT COUNT(*) FROM user_m_info where user_id=#{uid}")
    long countInfo(String uid);

    /**
     * 获取未读信息
     * @param uid
     * @return
     */
    @Select("SELECT * FROM info WHERE id in(SELECT info_id FROM user_m_info WHERE user_id=#{uid})")
    List<InfoVO> selectUnreadInfo(String uid);

    /**
     * 查看全部消息
     * @return
     */
    @Select("SELECT * FROM info")
    List<Info> selectAllInfo();

    /**
     * 用户查看具体信息
     * @param iid
     * @return
     */
    @Select("SELECT * FROM info WHERE id=#{iid}")
    Info userLookOverInfo(@Param("iid") String iid);

    /**
     * 新增查看记录
     * @param uid
     */
    @Insert("INSERT INTO user_m_info(user_id,info_id) VALUES(#{uid},#{iid})")
    void increaseLookOverRegister(@Param("uid") String uid,@Param("iid") String iid);
}
