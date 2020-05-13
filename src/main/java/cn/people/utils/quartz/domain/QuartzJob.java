package cn.people.utils.quartz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : FENGZHI
 * create at:  2020/5/13  下午9:41
 * @description: 任务实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuartzJob implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";

    private String id;
    /**定时器名称 */
    private String jobName;

    /**方法名称 */
    private String methodName;

    /**参数 */
    private String params;

    /**cron表达式 */
    private String cron;
}
