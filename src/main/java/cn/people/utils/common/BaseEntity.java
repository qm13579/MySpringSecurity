package cn.people.utils.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午10:07
 * @description:
 */
@Data
public class BaseEntity implements Serializable {
    public @interface Create {}
    public @interface Update {}
}
