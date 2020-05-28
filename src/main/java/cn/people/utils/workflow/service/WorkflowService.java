package cn.people.utils.workflow.service;

import cn.people.utils.workflow.dao.DateEvent;
import cn.people.utils.workflow.dao.EventBase;

public interface WorkflowService {

    /***
     * 创建工单模板
     * @param event
     */
    void increaseDateWorkflow(DateEvent event);

    /**
     * 添加工单用户审核配置
     */

    void increaseWorkflowConfig(String eventName);

    /**
     * 查询是否才存在审批工单
     * @return
     */
    DateEvent selectCheckDateWorkflow();
}
