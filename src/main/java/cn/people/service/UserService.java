package cn.people.service;

import cn.people.domain.UserInfo;
import cn.people.domain.vo.UserVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午3:38
 * @description: 用户接口
 */
public interface UserService {

    /**
     * 新增用户
     * @param userVO
     */
    void save(@Valid UserVO userVO);

    /**
     * 更新用户
     * @param userVO
     */
    void update(UserVO userVO);

    /**
     * 根据id删除用户
     * @param uid
     */
    void deleteUser(String uid);

    /**
     * 查找所有用户
     * @return
     */
    List<UserInfo> findAllUser();

    /**
     * 根据id查找用户
     * @param uid
     * @return
     */
    UserInfo findUserById(String uid);

    /**
     * 下载用户文件
     */
    void downloadUserFile();
}
