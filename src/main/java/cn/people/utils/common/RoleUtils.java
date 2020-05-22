package cn.people.utils.common;

import cn.people.domain.Role;
import org.apache.commons.lang3.StringUtils;

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

    /**
     * 判断是否为admin用户
     * @param roles
     * @return
     */
    public static boolean AssertRoleAdmin(List<Role> roles){
        for (Role role :roles) {
            if ("ROLE_ADMIN".equals(role.getAuthority())){
                return true;
            }
        }
        return false;
    }

    /**
     * 大写格式化
     * @param s
     * @return
     */
    public static String getUpper(String s){
        return StringUtils.upperCase(s);
    }

    /**
     * 小写格式化
     * @param s
     * @return
     */
    public static String getLower(String s){

        return StringUtils.lowerCase(s);
    }
}
