package cn.people.controller;

import cn.people.domain.Dep;
import cn.people.domain.vo.DepVO;
import cn.people.utils.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dep")
public class DepController {

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ApiOperation(value = "增加部门")
    public Result increase(@RequestBody DepVO dep){

        return Result.SUCCESS();
    }

    @RequestMapping(value = "delete/{did}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除部门")
    public Result deleteDep(@PathVariable("did") String did){
        return Result.SUCCESS();
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    @ApiOperation(value = "更新部门")
    public Result update(@RequestBody DepVO dep){
        return Result.SUCCESS();
    }

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    @ApiOperation(value = "查找全部")
    public Result findAll(){

        return Result.SUCCESS();

    }


}
