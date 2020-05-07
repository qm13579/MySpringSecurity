package cn.people.dao;

import cn.people.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午4:35
 * @description:
 */
public interface RoleMapper {
    /**
     * 通过userID查询对应角色
     * @param uid
     * @return
     */
    @Select("select * from role where id in (select role_id from user_m_role where user_id = #{uid}) ")
    @Results({
            @Result(property = "roleName",column = "role_name"),
            @Result(property = "id",column = "id"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.people.dao.PermissionMapper.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String uid);
}
