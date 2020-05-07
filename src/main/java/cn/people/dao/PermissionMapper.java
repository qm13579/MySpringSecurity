package cn.people.dao;

import cn.people.domain.Permission;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * 权限mapper
 * @author apple
 */
public interface PermissionMapper {

    @Select("select * from permission where id in (select permission_id from role_m_permission where role_id = #{rid} )")
    @Results({
            @Result(property = "permissionName",column = "permission")
    })
    List<Permission> findPermissionByRoleId(String rid);

}
