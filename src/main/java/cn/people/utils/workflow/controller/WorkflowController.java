package cn.people.utils.workflow.controller;

import cn.people.utils.common.Result;

import cn.people.utils.workflow.dao.DateEvent;
import cn.people.utils.workflow.dao.EventBase;
import cn.people.utils.workflow.service.WorkflowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @ApiOperation(value = "创建工单")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result increaseWorkflow(@RequestBody EventBase event){
        DateEvent dateEvent = new DateEvent();
        workflowService.increaseWorkflow(dateEvent);

        return Result.SUCCESS();
    }

}
