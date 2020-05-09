package cn.people.utils.jwt;

import cn.people.domain.UserInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader(JWTUtils.HEADER_TOKEN_NAME);
        if (token != null && token.trim().length()>0){
            String tokenBody = null;
            try {
                tokenBody = JWTUtils.testJwt(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tokenBody != null && tokenBody.trim().length() > 0 ){
                JSONObject user = JSON.parseObject(tokenBody).getJSONObject("user");
                UserInfo userInfo = JSON.toJavaObject(user,UserInfo.class);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo,null,userInfo.getAuthorities()));
            }else {
                HttpServletResponse res = (HttpServletResponse) servletResponse;
                res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                res.getWriter().write("{\"code\":\"405\",\"msg\":\"token错误\"}");
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
