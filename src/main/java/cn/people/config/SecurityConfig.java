package cn.people.config;

import cn.people.service.UserService;
import cn.people.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午3:35
 * @description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    /**
     * builder：典型用法1-自定义userDetailsService。2-提供若干个自定义的authenticationProvider
     *                3-设置双亲authenticationManager
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //定义密码验证及默认用户
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("test")
                .password(new BCryptPasswordEncoder().encode("test"))
                .roles("USER");
        auth.userDetailsService(userService);
        auth.eraseCredentials(false);
    }

    /**
     * 配置安全过滤器:
     *  配置登录界面、授权配置、scrf设置、跳转路径、逻辑认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable();

        http.authorizeRequests()   //
                .antMatchers("/user/create").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
