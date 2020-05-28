package cn.people.utils.workflow.utils;

import cn.people.domain.Role;
import cn.people.utils.workflow.dao.CheckUser;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/***
 * 工作流工具类
 */
public class WorkflowUtil {

    /**判断是否具有权限*/
    public static Boolean assetUserRole(List<Role> roles, List<CheckUser> checkUsers){

        for (Role role : roles) {
            boolean contains = checkUsers.contains(role.getAuthority());
            if (contains){
                return true;
            }
        }

        return false;
    }
}
