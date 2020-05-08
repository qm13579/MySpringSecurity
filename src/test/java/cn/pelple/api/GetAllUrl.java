package cn.pelple.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * 获取swagger全部api
 */

@Slf4j
public class GetAllUrl {

    @Resource
    WebApplicationContext applicationContext;

    public Object getAllUrl(){
        log.info("测试Api");
        RequestMappingHandlerMapping bean = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map map = bean.getHandlerMethods();
        System.out.println(map);

        return null;
    }
}
