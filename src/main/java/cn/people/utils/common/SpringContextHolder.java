package cn.people.utils.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author apple
 */
@Slf4j
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static <T> T getBean(String name){
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     *检查applicationContext不为空
     */
    private static void assertContextInjected(){
        if (applicationContext == null){
            throw new IllegalStateException("applicationContext属性未注入");
        }
    }
}
