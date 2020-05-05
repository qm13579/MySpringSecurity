package cn.people.service.impl;

import cn.people.dao.UserMapper;
import cn.people.domain.User;
import cn.people.domain.vo.UserVO;
import cn.people.service.UserService;
import cn.people.utils.common.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午8:38
 * @description:
 */
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
        User user = userMapper.findUserByUsername(s);
        if (user == null){
            throw new SecurityException("用户不存在");
        }
        return user;
    }

    @Override
    public void save(@Valid UserVO userVO) {
        if (userVO.getPassword1() != userVO.getPassword2()){
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
