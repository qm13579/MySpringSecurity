package cn.people.controller;

import cn.people.domain.Info;
import cn.people.domain.vo.InfoVO;
import cn.people.service.InfoService;
import cn.people.utils.common.BaseEntity;
import cn.people.utils.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @ApiOperation(value = "新增信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result increaseInfo(@Validated(BaseEntity.Create.class) @RequestBody InfoVO infoVO){
        infoService.increaseInfo(infoVO);
        return Result.SUCCESS();
    }

    @ApiOperation(value = "删除信息")
    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public Result deleteInfo(String id){
        infoService.deleteInfo(id);
        return Result.SUCCESS();
    }

    @ApiOperation(value = "更新消息")
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public Result updateInfo(@RequestBody InfoVO infoVO){
        infoService.updateInfo(infoVO);
        return Result.SUCCESS();
    }

    @ApiOperation(value = "查找全部信息")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result selectAllInfo(){
        Result result = Result.SUCCESS();
        List<Info> infos = infoService.selectAllInfo();
        result.setData(infos);
        return result;
    }

    @ApiOperation(value = "获取用户信息概况")
    @RequestMapping("/{uid}")
    public Result selectInfoByUser(@PathVariable("uid") String uid){
        Result result = Result.SUCCESS();
        List<Info> infos = infoService.selectRoughlyInfo();
        result.setData(infos);
        return result;
    }

    @ApiOperation(value = "查看详细信息")
    @RequestMapping(value = "/look/{uid}/{iid}")
    public Result  userLookOverInfo(@PathVariable("uid")String uid,@PathVariable("iid") String iid){
        Result result = Result.SUCCESS();
        Info info = infoService.userLookOverInfo(uid,iid);
        result.setData(info);
        return result;
    }

}
