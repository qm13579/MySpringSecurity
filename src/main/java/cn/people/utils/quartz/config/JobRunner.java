package cn.people.utils.quartz.config;

import cn.people.utils.quartz.QuartzManage;
import cn.people.utils.quartz.domain.QuartzJob;
import cn.people.dao.QuartzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/13  下午9:38
 * @description:
 * 项目启动后自动查询数据库，加载任务列表
 */
public class JobRunner implements ApplicationRunner {

    @Autowired
    private QuartzMapper quartzMapper;
    @Autowired
    private QuartzManage quartzManage;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<QuartzJob> quartzJobs = quartzMapper.findByIdIsFalse();
        quartzJobs.forEach(quartzManage::addJob);

    }
}
