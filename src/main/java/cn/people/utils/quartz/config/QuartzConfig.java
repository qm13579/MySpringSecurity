package cn.people.utils.quartz.config;

import org.quartz.Scheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author : FENGZHI
 * create at:  2020/5/13  下午9:30
 * @description:
 */
public class QuartzConfig {

    /**
     * 解决job中注入spring bean为null的问题
     */
    @Component("quartzJobFactory")
    public class QuartzJobFactory extends AdaptableJobFactory{

        @Autowired
        private AutowireCapableBeanFactory capableBeanFactory;

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            Object instance = super.createJobInstance(bundle);
            capableBeanFactory.autowireBean(instance);
            return instance;

        }
    }

    @Bean
    public Scheduler scheduler(QuartzJobFactory factory) throws Exception {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setJobFactory(factory);
        bean.afterPropertiesSet();
        Scheduler scheduler = bean.getScheduler();
        scheduler.start();
        return scheduler;

    }
}
