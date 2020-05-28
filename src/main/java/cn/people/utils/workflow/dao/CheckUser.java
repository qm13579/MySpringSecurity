package cn.people.utils.workflow.dao;

import cn.people.domain.Role;
import cn.people.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 审批人员类
 * @author apple
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckUser implements Serializable {

    private String id;
    private String level;
    private UserInfo user;
    private Role role;

    @Override
    public String toString() {
        return "CheckUser{" +
                "id='" + id + '\'' +
                ", level='" + level + '\'' +
                ", user=" + user +
                '}';
    }
}
