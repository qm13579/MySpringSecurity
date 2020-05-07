package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午4:14
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {
    private String id;
    private String permissionName;
    private List<Role> roles;

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", PermissionName='" + permissionName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
