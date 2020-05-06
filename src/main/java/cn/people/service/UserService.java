package cn.people.service;

import cn.people.domain.vo.UserVO;

import javax.validation.Valid;

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

    void update(UserVO userVO);
}
