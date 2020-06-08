package cn.people.utils.workflow.mapper;

import cn.people.domain.Dep;
import cn.people.utils.workflow.dao.CheckUser;
import cn.people.utils.workflow.dao.DateEvent;
import cn.people.utils.workflow.dao.EventBase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkflowMapper {

    /**
     * 创建日期类工单
     * @param event
     */
    void increaseDateWorkflow(DateEvent event);

    /**
     * 查询日期类审批深度
     * @return
     */
    int selectCheckDeep();

    /**
     * 查询具有审批权限的角色
     * @return
     */
    @Select("SELECT * FROM workflow_check_user WHERE level <= #{deep}")
    List<CheckUser> selectCheckUser(int deep);

    /**
     * 向workflow_m_check_user中插入一条记录
     * @param checkUserId
     * @param workflowId
     */
    @Insert("Insert into workflow_m_check_user (check_user_id,workflow_id) VALUES (#{checkUserId},#{workflowId})")
    void increaseMiddleWorkflow(@Param("checkUserId") String checkUserId,@Param("workflowId") String workflowId);

    /**
     * 增加日期类工单审核配置配置
     */
    @Insert("INSERT INTO workflow_config(workflow_id,check_user_id) VALUES(#{workflow_id},#{deep})")
    void increaseWorkflowConfig(@Param("workflow_id") String workflow_id,@Param("deep") Integer deep);

    /**查询是否才存在审批工单*/
    DateEvent selectCheckWorkflow();

    /***
     * 获取汗布审核角色
     * @return
     */
    List<CheckUser> selectCheckUserAll();

    DateEvent selectCheckWorkflowByRole(Dep dep);
}
