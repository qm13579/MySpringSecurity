package cn.pelple;

import cn.pelple.api.GetAllUrl;
import cn.people.MyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyApplication.class})
public class ApiTest {

    @Test
    public void testApi(){
        GetAllUrl getAllUrl = new GetAllUrl();
        getAllUrl.getAllUrl();
    }
}
