package cn.people.utils.quartz.task;

import org.springframework.stereotype.Component;

/**
 * @author apple
 */
@Component
public class TestTask {
    public void run(){
        System.out.println("this is run");
    }
}
