package cn.people.utils.quartz;

import cn.people.utils.common.SpringContextHolder;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class QuartzRunner implements Callable {

    private Object target;
    private Method method;
    private String params;

    public QuartzRunner(String beanName, String methodName, String params) throws NoSuchMethodException {
        this.target = SpringContextHolder.getBean(beanName);
        this.params = params;
        if (params != null){
            this.method = target.getClass().getDeclaredMethod(methodName,String.class);
        }else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public Object call() throws Exception {
        ReflectionUtils.makeAccessible(method);
        if (params != null){
            method.invoke(target,params);
        }else {
            method.invoke(target);
        }
        return null;
    }
}
