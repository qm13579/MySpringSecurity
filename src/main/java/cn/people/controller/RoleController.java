package cn.people.controller;

import cn.people.domain.Role;
import cn.people.domain.vo.RoleVO;
import cn.people.utils.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController {

    @ApiOperation(value = "创建用户")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result createRole(@RequestBody RoleVO role){
        return Result.SUCCESS();
    }

    @ApiOperation(value = "更新用户")
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public Result updateRole(){
        return Result.SUCCESS();
    }

    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public Result deleteRole(){
        return Result.SUCCESS();
    }

    @ApiOperation(value = "查找所有用户")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result selectRoleAll(){

        Result result = Result.SUCCESS();
        return result;
    }

}
