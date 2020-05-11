package cn.people.dao;

import cn.people.domain.Dep;
import cn.people.domain.vo.DepVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepMapper {

    /**
     * 查找全部
     * @return
     */
    @Select("select * from department")
    @Results({
            @Result(property = "depName",column = "dep_name")
    })
    List<DepVO> findALL();

    /**
     * 删除用户
     * @param did
     */
    @Delete("delete into department where id = #{id}")
    void deleteDep(String did);

    /**
     * 更新部门名称
     * @param dep
     */
    @Update("update set department dep_name=#{depName} where id = #{id}")
    void updateDepName(Dep dep);


    @Update("update set department dep_name=#{depName} where id = #{id}")
    void updateDep(Dep dep);

    /**
     * 增加部门
     * @param dep
     */
    @Insert("insert into department(id,dep_name,level) values(#(id),#{depName},#{level})")
    void increase(DepVO dep);

    /**
     * 根据id查找
     * @return
     */
    @Select("select * from department where id = #{id}")
    @Results({
            @Result(property = "userInfos",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.people.dao.UserMapper.findUserById"))
    })
    Dep findDepById(String id);
}
