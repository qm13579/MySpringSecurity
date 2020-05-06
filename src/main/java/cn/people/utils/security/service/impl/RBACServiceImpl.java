package cn.people.utils.security.service.impl;

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
        Object principal = authentication.getPrincipal();
        log.info(principal.toString());
        boolean permission = false;
        if (principal instanceof UserDetails){
            UserInfo user = ((UserInfo) principal);
            HashSet<String> urls = new HashSet<>();
            for (Role role:user.getRoles()) {
                for (Permission per :role.getPermissions()){
                    urls.add(per.getPermissionName());
                }
            }
            for (String url :urls){
                if (antPathMatcher.match(url,request.getRequestURI())){
                    permission = true;
                    break;
                }
            }
        }
        return permission;
    }

}
