package cn.people.utils.quartz.task;

import org.springframework.stereotype.Component;

@Component
public class TestTask {
    public void run(){
        System.out.println("this is run");
    }
}
