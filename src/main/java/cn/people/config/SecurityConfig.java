package cn.people.config;

import cn.people.service.UserService;
import cn.people.service.impl.UserServiceImpl;
import cn.people.utils.security.handle.AuthLimitHandler;
import cn.people.utils.security.handle.LoginFailureHandler;
import cn.people.utils.security.handle.LoginSuccessHandler;
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
        auth.inMemoryAuthentication()
                .withUser("test")
                .password(new BCryptPasswordEncoder().encode("test"))
                .roles("USER");
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
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
        http.authorizeRequests()
                .antMatchers("/user/create").permitAll()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
            .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new LoginSuccessHandler())  //登陆成功策略
                .failureHandler(new LoginFailureHandler()) //登录失败策略
                .and()
            .exceptionHandling()
                .accessDeniedHandler(new AuthLimitHandler());//权限不足策略
//                .authenticationEntryPoint()登陆过期策略

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
