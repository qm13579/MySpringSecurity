package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 部门实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dep {

    private String id;
    private String depName;
    private String level;
    private List<UserInfo> userInfos;

    @Override
    public String toString() {
        return "Dep{" +
                "id='" + id + '\'' +
                ", depName='" + depName + '\'' +
                ", level='" + level + '\'' +
                ", userInfos=" + userInfos +
                '}';
    }
}
