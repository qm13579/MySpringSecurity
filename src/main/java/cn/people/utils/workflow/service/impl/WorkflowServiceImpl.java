package cn.people.utils.workflow.service.impl;

import cn.people.utils.workflow.dao.EventBase;
import cn.people.utils.workflow.service.WorkflowService;
import org.springframework.stereotype.Service;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Override
    public void increaseWorkflow(EventBase event) {
        // 创建工单 ---->时间性工单、数量工单、路程性工单、文件性工单

    }
}
