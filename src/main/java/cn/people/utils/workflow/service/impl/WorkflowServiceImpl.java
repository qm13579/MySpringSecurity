package cn.people.utils.workflow.service.impl;

import cn.people.domain.UserInfo;
import cn.people.utils.common.IdWorker;
import cn.people.utils.common.SecurityUtils;
import cn.people.utils.workflow.dao.CheckUser;
import cn.people.utils.workflow.dao.DateEvent;
import cn.people.utils.workflow.mapper.WorkflowMapper;
import cn.people.utils.workflow.service.WorkflowService;
import cn.people.utils.workflow.utils.WorkflowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author apple
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private IdWorker idWorker;

    @Resource
    private WorkflowMapper workflowMapper;

    @Override
    public void increaseDateWorkflow(DateEvent event) {
        event.setId(idWorker.nextId()+"");
        // 创建工单 ---->时间性工单、数量工单、路程性工单、文件性工单
        workflowMapper.increaseDateWorkflow(event);
        // 获取审批深度，根据审批深度确定审批人员，深度不能超过当前审批角色个数(缓存)
        int deep = workflowMapper.selectCheckDeep();
        // 确定审批人员dep、leader，人事归档？(缓存)
        List<CheckUser> checkUsers = workflowMapper.selectCheckUser(deep);
        event.setCheckUsers(checkUsers);
        checkUsers.forEach(
                (check) ->{
                    workflowMapper.increaseMiddleWorkflow(check.getId(),event.getId());
                }
        );

    }

    @Override
    public void increaseWorkflowConfig(String eventName) {

        //新增工单审核配置
        workflowMapper.increaseWorkflowConfig(eventName,1);
    }

    @Override
    public DateEvent selectCheckDateWorkflow() {

        //查看是否存在有审批的权限
        List<CheckUser>  checkUsers = workflowMapper.selectCheckUserAll();
        UserInfo user = SecurityUtils.getCurrentUser();
        Boolean permission = WorkflowUtil.assetUserRole(user.getAuthorities(), checkUsers);
        if (!permission){
            return null;
        }
        //获取当前用户的最大角色

        //查询数据库是否有存在审批的工单
        DateEvent event = workflowMapper.selectCheckWorkflowByRole(user.getDep());
        //有则返回

        return null;
    }

}
