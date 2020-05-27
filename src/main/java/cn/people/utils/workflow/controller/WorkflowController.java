package cn.people.utils.workflow.controller;

import cn.people.utils.common.Result;

import cn.people.utils.workflow.dao.AmountEvent;
import cn.people.utils.workflow.dao.DateEvent;
import cn.people.utils.workflow.dao.EventBase;
import cn.people.utils.workflow.service.WorkflowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author apple
 */
@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @ApiOperation(value = "创建时间工单")
    @RequestMapping(value = "date",method = RequestMethod.POST)
    public Result increaseDateWorkflow(@RequestBody DateEvent event){
        workflowService.increaseWorkflow(event);
        return Result.SUCCESS();
    }

    @ApiOperation(value = "创建数量工单")
    @RequestMapping(value = "amount",method = RequestMethod.POST)
    public Result increaseAmount(@RequestBody AmountEvent amountEvent){

        return Result.SUCCESS();
    }



}
