package cn.people.service;

import cn.people.domain.Permission;

import java.util.List;

/**
 * 权限业务接口
 * @author : FENGZHI
 * create at:  2020/5/7  下午9:27
 * @description:
 */
public interface PermissionService {
    /**
     * 添加权限
     * @param permission
     */
    void increasePermission(Permission permission);

    /**
     * 更新权限
     * @param permission
     */
    void updatePermission(Permission permission);

    /**
     * 根据id删除权限
     * @param pid
     */
    void deletePermission(String pid);

    /**
     * 查找所有权限
     * @return
     */
    List<Permission> findAllPermission();

    /**
     * 根据角色id查找权限
     * @param rid
     * @return
     */
    List<Permission> findAllPermissionByRoleId(String rid);

}
