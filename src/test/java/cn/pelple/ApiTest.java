package cn.pelple;

import cn.pelple.api.GetAllUrl;
import cn.people.MyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class ApiTest {

    @Test
    public void testApi(){
        MyThread myThread = new MyThread();
        myThread.start();
    }

}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("this is thread1");
    }
}