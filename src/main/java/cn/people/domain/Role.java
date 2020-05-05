package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午4:12
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private String id;
    private String roleName;
    private List<Permission> permissions;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
