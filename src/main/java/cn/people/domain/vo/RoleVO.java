package cn.people.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO implements Serializable {

    private String id;
    private String authority;
    private String desc;
    /**数据范围 1所有数据，2：自定义数据 3：本部门数据权限 4：本部门以下数据权限*/
    private String dataScope;

    private List<DepVO> deps;

    public RoleVO(String id, String authority, String desc, String dataScope) {
        this.id = id;
        this.authority = authority;
        this.desc = desc;
        this.dataScope = dataScope;
    }

    @Override
    public String toString() {
        return "RoleVO{" +
                "id='" + id + '\'' +
                ", authority='" + authority + '\'' +
                ", desc='" + desc + '\'' +
                ", dataScope='" + dataScope + '\'' +
                '}';
    }
}
