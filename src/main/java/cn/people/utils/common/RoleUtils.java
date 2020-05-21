package cn.people.utils.common;

import cn.people.domain.Role;

import java.util.List;

/**
 * 角色工具类
 * @author : FENGZHI
 * create at:  2020/5/21  下午9:23
 * @description:
 */
public class RoleUtils {
    /**
     * 获取最大角色
     * @param roles
     * @return
     */
    public static Role getRoleMax(List<Role> roles){
        Role maxRole = null;
        int max = 0;

        for (Role role:roles) {
            int anInt = Integer.parseInt(role.getDataScope());
            if (max == 0){
                max = anInt;
                maxRole = role;
            }
            if (max < anInt) {
                max = anInt;
                maxRole = role;
            }
        }
        return maxRole;
    }
}
