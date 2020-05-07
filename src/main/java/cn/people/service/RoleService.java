package cn.people.service;

import cn.people.domain.Role;

import java.util.List;

/**
 * 角色接口
 * @author : FENGZHI
 * create at:  2020/5/7  下午9:28
 * @description:
 */
public interface RoleService {
    /**
     * 新增角色
     * @param role
     */
    void increaseRole(Role role);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 查找角色
     * @param rid
     * @return
     */
    Role findRole(String rid);

    /**
     * 获取所有角色
     * @return
     */
    List<Role> findRoles();

    /**
     * 通过id删除角色
     * @param id
     */
    void deleteRoleById(String id);

    /**
     * 通过id批量删除角色
     * @param rid
     */
    void deleteAllRole(List<String> rid);

    /**
     * 初始化角色
     */
    void initRole();
}
