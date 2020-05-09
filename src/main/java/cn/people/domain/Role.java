package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

/**
 * 实现grantedAuthority接口 token转回登录用户
 * 用户实体类和token中的字段名一致
 * @author : FENGZHI
 * create at:  2020/5/5  下午4:12
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    private String id;
    private String authority;
    private List<Permission> permissions;
    private List<Menum> menums;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", authority='" + authority + '\'' +
                ", permissions=" + permissions +
                ", menums=" + menums +
                '}';
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
