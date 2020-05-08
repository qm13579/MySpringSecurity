package cn.people.dao;

import cn.people.domain.Role;
import org.apache.ibatis.annotations.*;

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
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.people.dao.PermissionMapper.findPermissionByRoleId")),
            @Result(property = "menums",column = "id" ,javaType = java.util.List.class, many = @Many(select = "cn.people.dao.MenumMapper.findMenumByRoleId"))
    })
    List<Role> findRoleByUserId(String uid);

    /**
     * 创建user与role关联
     * @param uid
     * @param rid
     */
    @Insert("insert into user_m_role(user_id,role_id) values (#{uid},#{rid})")
    void createUserMRole(@Param("uid") String uid, @Param("rid") String rid);
}
