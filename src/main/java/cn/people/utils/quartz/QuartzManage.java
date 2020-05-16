package cn.people.utils.quartz;

import cn.people.utils.quartz.domain.QuartzJob;
import cn.people.utils.quartz.job.ExecutionJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static org.quartz.TriggerBuilder.newTrigger;
/**
 * @author : FENGZHI
 * create at:  2020/5/13  下午9:46
 * @description:
 */
@Component
public class QuartzManage {

    public static final Logger logger = LoggerFactory.getLogger(QuartzManage.class);
    public static final String JOB_NAME = "TASK_";

    @Autowired
    private Scheduler scheduler;

    public void addJob(QuartzJob quartzJob) {
        //构建job信息
        JobDetail build = JobBuilder.newJob(ExecutionJob.class)
                .withIdentity(JOB_NAME + quartzJob.getId())
                .build();
        //构建触发器
        CronTrigger cronTrigger = newTrigger()
                .withIdentity(JOB_NAME + quartzJob.getId())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCron()))
                .build();
        //创建数据容器
        cronTrigger.getJobDataMap().put(QuartzJob.JOB_KEY,quartzJob);
        //加入scheduler调度
        try {
            scheduler.scheduleJob(build,cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
