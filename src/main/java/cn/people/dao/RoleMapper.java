package cn.people.dao;

import cn.people.domain.Role;
import cn.people.domain.vo.RoleVO;
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
            @Result(property = "authority",column = "role_name"),
            @Result(property = "id",column = "id"),
            @Result(property = "dataScope",column = "data_scope"),
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
    /**
     * 新增角色
     * @param role
     */
    @Insert("insert into role(id,role_name,data_scope,desc) values(#{id},#{roleName},#{dataScope},#{desc})")
    void increaseRole(RoleVO role);
    /**
     * 更新角色信息
     * @param role
     */
    @Update("update set role role_name=#{roleName},data_scope=#{dataScope},desc=#{desc}")
    void updateRole(RoleVO role);
    /**
     * 查看角色
     * @param rid
     * @return
     */
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(property = "authority",column = "role_name"),
            @Result(property = "id",column = "id"),
            @Result(property = "dataScope",column = "data_scope"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.people.dao.PermissionMapper.findPermissionByRoleId")),
            @Result(property = "menums",column = "id" ,javaType = java.util.List.class, many = @Many(select = "cn.people.dao.MenumMapper.findMenumByRoleId"))
    })
    Role findRole(String rid);
    /**
     * 查找所有角色
     * @return
     */
    @Select("select * from role")
    List<Role> findRoles();
    /**
     * 删除user_m_role中间表中的角色信息
     * @param id
     */
    @Delete("delete from user_m_role where role_id = #{id}")
    void deleteUserMRole(String id);
    /**
     * 删除role表中的角色信息
     * @param id
     */
    @Delete("delete from role where id = #{id}")
    void deleteRole(String id);

    @Select("select count(*) from role_m_dep where role_id =#{id}")
    long findRoleMDep(String id);

    /**
     * 删除role_m_dep中的角色信息
     * @param id
     */
    @Delete("delete from role_m_dep where role_id = #{id}")
    void deleteRoleMDep(String id);

    /**
     * 统计role中的个数
     * @return
     */
    @Select("select count(*) from role")
    long countRole();

    /**
     * role_m_dep新增角色和部门之间关联
     * @param rid
     * @param did
     */
    @Insert("insert into role_m_dep(role_id,dep_id) values(#{rid},#{did})")
    void increaseRoleMDep(@Param("rid")String rid, @Param("dId")String did );

}
