package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午3:38
 * @description: 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements UserDetails {

    private String id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Boolean status;
    private Dep dep;
    private List<Role> authorities ;

    /* 指示是否未过期的用户的凭据(密码),过期的凭据防止认证 默认true 默认表示未过期 */
    private boolean credentialsNonExpired = true;

    //账户是否未过期,过期无法验证 默认true表示未过期
    private boolean accountNonExpired = true;

    //用户是未被锁定,锁定的用户无法进行身份验证 默认true表示未锁定
    private boolean accountNonLocked = true;

    //是否可用 ,禁用的用户不能身份验证 默认true表示可用
    private boolean enabled = true;

    @Override
    public List<Role> getAuthorities(){
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", roles=" + authorities  +
                '}';
    }
}
