package cn.people.utils.workflow.service.impl;

import cn.people.utils.workflow.dao.DateEvent;
import cn.people.utils.workflow.dao.EventBase;
import cn.people.utils.workflow.service.WorkflowService;
import org.springframework.stereotype.Service;

/**
 * @author apple
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Override
    public void increaseWorkflow(DateEvent event) {
        // 创建工单 ---->时间性工单、数量工单、路程性工单、文件性工单
        // 获取审批深度，根据审批深度确定审批人员，深度不能超过当前审批角色个数
        // 确定审批人员dep、leader，人事归档？


    }
}
