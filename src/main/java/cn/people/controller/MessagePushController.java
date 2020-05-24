package cn.people.controller;

import cn.people.utils.common.Result;
import cn.people.utils.websocket.WebSocketService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : FENGZHI
 * create at:  2020/5/22  下午11:03
 * @description:
 */
@RestController
@RequestMapping("message")
public class MessagePushController {

    @ApiOperation(value = "全部消息推送")
    @RequestMapping(value = "all",method = RequestMethod.GET)
    public Result push(){
        return Result.SUCCESS();
    }

}
