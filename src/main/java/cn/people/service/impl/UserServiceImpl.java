package cn.people.service.impl;

import cn.people.dao.RoleMapper;
import cn.people.dao.UserMapper;
import cn.people.domain.UserInfo;
import cn.people.domain.vo.UserVO;
import cn.people.service.UserService;
import cn.people.utils.aspect.annotation.DataScope;
import cn.people.utils.common.ExcelUtils;
import cn.people.utils.common.IdWorker;
import cn.people.utils.common.PDFUtils;
import cn.people.utils.factory.FileHandleFactory;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午8:38
 * @description:
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
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
        roleMapper.createUserMRole(userVO.getId(),"user");
        userMapper.save(userVO);
    }

    @Override
    public void update(UserVO userVO) {
        if (userVO.getPassword1() != userVO.getPassword2()){
            throw new SecurityException("密码不一致");
        }

    }

    @Override
    public void deleteUser(String uid) {

    }

    @DataScope(userAlias = "u",deptAlias = "d")
    @Override
    public List<UserInfo> findAllUser() {
        List<UserInfo> user = userMapper.findUser();
        return user;
    }

    @Override
    public UserInfo findUserById(String uid) {
        return null;
    }

    @Override
    public void downloadUserFile(HttpServletResponse response)  {
        List<UserInfo> list = userMapper.findUser();
        ExcelUtils excelHandle = FileHandleFactory.getExcelHandle();
        try {
            excelHandle.export("user",list,response);
        }catch (Exception e){
            logger.debug("文件下载失败");
            logger.debug(e.toString());
        }
    }

    @Override
    public void preview(HttpServletResponse response) {
        List<UserInfo> list = userMapper.findUser();
        PDFUtils pdfHandle = FileHandleFactory.getPdfHandle();
        try {
            pdfHandle.getPdfFile(response,list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("文件预览失败");
            logger.debug(e.toString());
        }
    }

}
