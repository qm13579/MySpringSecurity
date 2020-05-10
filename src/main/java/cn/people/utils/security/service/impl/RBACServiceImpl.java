package cn.people.utils.security.service.impl;

import cn.people.domain.Menum;
import cn.people.domain.Permission;
import cn.people.domain.Role;
import cn.people.domain.UserInfo;
import cn.people.utils.security.service.RBACService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

@Slf4j
@Component("rbacService")
public class RBACServiceImpl implements RBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        log.info("正在验证:"+request.getRequestURL());
        boolean methodAssert = false;
        boolean urlAssert = false;
        String method = request.getMethod().toString();
        Object principal = authentication.getPrincipal();
        boolean permission = false;
        if (principal instanceof UserDetails){
            UserInfo user = ((UserInfo) principal);
            log.info(user.toString());
            HashSet<String> urls = new HashSet<>();
            //添加url到set集合（去重）
            System.out.println(user);
            for (Role role:user.getAuthorities() ) {
                for (Permission per :role.getPermissions()){
                    if (antPathMatcher.match(per.getMethod(),method)){
                        methodAssert = true;
                    }
                    urls.add(per.getPermissionName());
                }
            }
            //匹配url
            for (String url :urls){

                if (antPathMatcher.match(url,request.getRequestURI())){
                    urlAssert = true;
                    break;
                }
            }
        }
        //同为true才具有权限
        if (methodAssert && urlAssert){
            permission = true;
        }
        System.out.println(permission);
        return permission;
    }

}
