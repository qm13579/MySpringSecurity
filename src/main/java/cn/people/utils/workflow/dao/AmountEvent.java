package cn.people.utils.workflow.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数量工单
 * @author : FENGZHI
 * create at:  2020/5/27  下午8:32
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountEvent extends EventBase {

    private final String EVENT_NAME = "amount";
    /**工单数量*/
    private double amount;


}
