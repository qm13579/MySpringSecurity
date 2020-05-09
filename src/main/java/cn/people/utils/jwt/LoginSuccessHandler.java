package cn.people.utils.jwt;

import cn.people.domain.UserInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录成功后handler
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        boolean loginBoolean = true;
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();

        userInfo.setPassword(null); //?
        long now = System.currentTimeMillis();
        JSONObject payload = new JSONObject();
        payload.put("iss","sys"); //签发人
        payload.put("aud",userInfo.getUsername()); //受众
        payload.put("exp",now + JWTUtils.EXPIRE_TIME); //过期时间
        payload.put("nbf",now); //生效时间
        payload.put("jti",userInfo.getId()); //编号
        payload.put("user",userInfo); //用户对象
        payload.put("sub","JWT-TEST"); //主题

        try {
            httpServletResponse.setHeader(JWTUtils.HEADER_TOKEN_NAME,new JWT(payload.toJSONString()).toString() );
        } catch (Exception e) {
            loginBoolean = false;
        }
        if (loginBoolean){
            httpServletResponse.getWriter().write("{\"code\":\"200\",\"msg\":\"登录成功\"}");
        }else {
            httpServletResponse.getWriter().write("{\"code\":\"500\",\"msg\":\"登录失败\"}");


        }
    }
}
