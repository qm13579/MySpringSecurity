package cn.people.domain.vo;

import cn.people.utils.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午9:07
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends BaseEntity implements Serializable {
    @NotBlank(groups = {Update.class})
    private String id;

    @NotBlank(groups = {Create.class})
    private String username;

    private String password;
    @NotBlank
    private String password1;
    @NotBlank
    private String password2;
    @Email(groups = {Create.class})
    private String email;
    @NotBlank(groups = {Create.class})
    private String mobile;

    @Override
    public String toString() {
        return "UserVO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
