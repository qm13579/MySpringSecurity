package cn.people.controller;

import cn.people.domain.UserInfo;
import cn.people.domain.vo.UserVO;
import cn.people.service.UserService;
import cn.people.utils.aspect.annotation.Limit;
import cn.people.utils.common.Result;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        logger.info("创建用户");
        userService.save(userVO);
        return Result.SUCCESS();
    }

    @ApiOperation(value = "更改用户密码")
    @RequestMapping(value = "password",method = RequestMethod.PUT)
    public Result updatePassword(@Validated(UserVO.Update.class) @RequestBody UserVO userVO){
        userService.update(userVO);
        return Result.SUCCESS();
    }

    @ApiOperation(value = "查找所有用户")
    @RequestMapping(value = "find",method = RequestMethod.GET)
    public Result findUser(Model model){
        Result result = Result.SUCCESS();
        List<UserInfo> allUser = userService.findAllUser();
        result.setData(allUser);
        return result;
    }

    @ApiOperation(value = "下载用户数据")
    @RequestMapping(value = "download",method = RequestMethod.GET)
    public void downloadUserFile(HttpServletResponse response){
        userService.downloadUserFile(response);
    }

    @Limit(name = "文件预览",key = "preview",count = 10,period = 60)
    @ApiOperation(value = "预览文件")
    @RequestMapping(value = "preview",method = RequestMethod.GET)
    public void pdfPreview(HttpServletResponse response){
        userService.preview(response);
    }

    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public Result updateUserInfo(@RequestBody UserVO user){
        userService.updateUserInfo(user);
        return Result.SUCCESS();
    }
    @ApiOperation(value = "用户停用")
    @RequestMapping(value = "{uid}",method = RequestMethod.DELETE)
    public Result stopUser(@PathVariable("uid") String uid){
        userService.stopUser(uid);
        return Result.SUCCESS();
    }

    @ApiOperation(value = "退出登陆")
    @RequestMapping(value = "out/{uid}",method = RequestMethod.GET)
    public Result signOut(@PathVariable("uid") String uid){
        return Result.SUCCESS();
    }

    @ApiOperation(value = "上传用户照片")
    @RequestMapping(value = "fileLoad",method = RequestMethod.POST)
    public Result fileLoad(@RequestParam MultipartFile file){
        try {
            userService.fileLoad(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.SUCCESS();
    }

    @ApiOperation(value = "批量上传用户信息")
    @RequestMapping(value = "loadUserFile")
    public Result LoadUserFile(@RequestParam MultipartFile file){
        userService.LoadUserFile(file);
        Result result = Result.SUCCESS();
        return result;
    }

}
