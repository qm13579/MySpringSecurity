package cn.people.dao.provider;

import cn.people.domain.UserInfo;
import cn.people.utils.aspect.DataScopeAspect;
import cn.people.utils.common.SecurityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户提供类
 * @author apple
 */
public class UserProvider {

    public String selectAllUser(){
        StringBuffer sql = new StringBuffer("select * from user_info");
        UserInfo user = SecurityUtils.getCurrentUser();
        String str = (String) user.getParams().get(DataScopeAspect.DATA_SCOPE);
        if (str != null){
            sql.append(" "+str);
        }
        return sql.toString();
    }

}
