package cn.people.utils.workflow.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/***
 * 日期时间类-->请假审批
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateEvent extends EventBase implements Serializable {

    /**事件名称*/
    private final String EVENT_NAME = "date_event";

    /**事件结束事件*/
    private String endDate;

    @Override
    public String toString() {
        return "DateEvent{" +
                "endDate='" + endDate + '\'' +
                '}';
    }
}
