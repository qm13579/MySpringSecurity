package cn.people.utils.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static <T> T getBean(String name){
         return (T) applicationContext.getBean(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextHolder.applicationContext != null){

        }
        SpringContextHolder.applicationContext = applicationContext;
    }
}
