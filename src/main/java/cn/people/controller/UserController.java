package cn.people.controller;

import cn.people.domain.vo.UserVO;
import cn.people.service.UserService;
import cn.people.utils.common.Result;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午9:14
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Result createUser(@Validated(UserVO.Create.class) @RequestBody UserVO userVO){
        userService.save(userVO);
        return Result.SUCCESS();
    }

    @ApiOperation(value = "更改用户密码")
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Result updatePassword(@Validated(UserVO.Update.class) @RequestBody UserVO userVO){
        userService.update(userVO);
        return Result.SUCCESS();
    }
    @ApiOperation(value = "查找所有用户")
    @RequestMapping(value = "find",method = RequestMethod.GET)
    public Result findUser(){
        return Result.SUCCESS();
    }
}