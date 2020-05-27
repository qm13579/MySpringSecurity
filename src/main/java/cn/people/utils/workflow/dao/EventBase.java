package cn.people.utils.workflow.dao;

import cn.people.domain.UserInfo;
import cn.people.domain.vo.UserVO;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/***
 * 事件
 * @author apple
 */
@Data
public class EventBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private  String EVENT_NAME;

    private String id;
    /**事件创建时间*/
    private String createData;
    /**事件内容*/
    private String context;
    /**涉及审批人员*/
    private List<CheckUser> checkUsers;
    /**创建人*/
    private UserVO user;
    /**审批深度*/
    private Integer checkDeep;

}
