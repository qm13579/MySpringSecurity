package cn.people.utils.common;

import cn.people.domain.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    /**
     * 获取当前登录用户
     * @return
     */
    public static UserInfo getCurrentUser(){
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
