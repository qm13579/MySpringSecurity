package cn.people.dao;

import cn.people.domain.Menum;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/7  下午9:06
 * @description:
 */
public interface MenumMapper {

    void increaseMenum();

    /**
     * 通过角色查询菜单
     * @param rid
     * @return
     */
    @Select("select * from menum where id in (select menum_id from role_m_menum where role_id=#{rid})")
    List<Menum> findMenumByRoleId(String rid);


}
