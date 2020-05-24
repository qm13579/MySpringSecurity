package cn.people;

import cn.people.utils.common.IdWorker;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午3:26
 * @description:
 */
@EnableWebSocket
@MapperScan("cn.people.dao")
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }

    
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
