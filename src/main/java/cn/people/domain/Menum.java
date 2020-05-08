package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.print.DocFlavor;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : FENGZHI
 * create at:  2020/5/7  下午9:03
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menum implements Serializable {

    private String id;

    @NotBlank
    private String menumName;
    private String level;

    @Override
    public String toString() {
        return "Menum{" +
                "id='" + id + '\'' +
                ", menumName='" + menumName + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
