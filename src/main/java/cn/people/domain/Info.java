package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消息实体类
 * @author : FENGZHI
 * create at:  2020/5/23  下午2:42
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info implements Serializable {

    private String id;
    /**消息内容*/
    private String context;
    /**消息时间*/
    private String date;
    /**发布人*/
    private UserInfo userInfo;
    /**消息状态*/
    private Integer status;

    @Override
    public String toString() {
        return "Info{" +
                "id='" + id + '\'' +
                ", context='" + context + '\'' +
                ", date='" + date + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
