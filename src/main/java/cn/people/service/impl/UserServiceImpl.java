package cn.people.service.impl;

import cn.people.dao.UserMapper;
import cn.people.domain.Role;
import cn.people.domain.UserInfo;
import cn.people.domain.vo.UserVO;
import cn.people.service.UserService;
import cn.people.utils.common.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午8:38
 * @description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo user = userMapper.findUserByUsername(s);
        if (user == null){
            throw new SecurityException("用户不存在");
        }
        return user;
    }


    @Override
    public void save(@Valid UserVO userVO) {
        if ( !(userVO.getPassword1().equals(userVO.getPassword2())) ){
            throw  new SecurityException("密码不一致");
        }
        userVO.setId(idWorker.nextId()+"");
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword1()));

        userMapper.save(userVO);
    }

    @Override
    public void update(UserVO userVO) {
        if (userVO.getPassword1() != userVO.getPassword2()){
            throw new SecurityException("密码不一致");
        }

    }
}
