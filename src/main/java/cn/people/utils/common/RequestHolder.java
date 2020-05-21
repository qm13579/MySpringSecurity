package cn.people.utils.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RequestHolder {

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getIp(HttpServletRequest request){
        return null;
    }
}
