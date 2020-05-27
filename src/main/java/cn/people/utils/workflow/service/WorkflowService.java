package cn.people.utils.workflow.service;

import cn.people.utils.workflow.dao.EventBase;

public interface WorkflowService {

    /***
     * 创建工单模板
     * @param event
     */
    void increaseWorkflow(EventBase event);

}
