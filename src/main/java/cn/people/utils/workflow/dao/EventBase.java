package cn.people.utils.workflow.dao;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/***
 * 事件
 */
@Data
public class EventBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**事件创建时间*/
    private String createData;
    /**事件内容*/
    private String context;
    /**涉及审批人员*/
    private List<CheckUser> checkUsers;

}
