package cn.people.utils.quartz.job;

import cn.people.utils.common.ThreadPool;
import cn.people.utils.quartz.QuartzRunner;
import cn.people.utils.quartz.domain.QuartzJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : FENGZHI
 * create at:  2020/5/13  下午9:53
 * @description:
 */
@Async
public class ExecutionJob extends QuartzJobBean {

    private final static ThreadPoolExecutor EXECUTOR = ThreadPool.getPool();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        try{
            QuartzRunner task = new QuartzRunner(quartzJob.getJobName(), quartzJob.getMethodName(), quartzJob.getParams());
            Future future = EXECUTOR.submit(task);
            future.get();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
