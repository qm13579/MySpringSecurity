package cn.people.domain.vo;

import cn.people.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepVO implements Serializable {

    private String id;
    @NotBlank
    private String depName;
    @NotBlank
    private String level;
    @NotBlank
    private String upperLevel;

    private List<DepVO> children;


    @Override
    public String toString() {
        return "DepVO{" +
                "id='" + id + '\'' +
                ", depName='" + depName + '\'' +
                ", level='" + level + '\'' +
                ", upperLevel='" + upperLevel + '\'' +
                '}';
    }
}
