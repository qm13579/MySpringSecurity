package cn.pelple;
import cn.people.utils.workflow.dao.DateEvent;
import cn.people.utils.workflow.dao.EventBase;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class ApiTest {

    static class Likes implements ILike{

        @Override
        public void like() {
            System.out.println("love2");
        }
    }

    @Test
    public void test3(){
        String file = "1.lxl";
        String substring = file.substring(file.lastIndexOf("."));
        boolean equals = ".lxl".equals(substring);
        System.out.println(substring+equals);
    }
    @Test
    public void testApi(){

        class Like2 implements ILike{

            @Override
            public void like() {
                System.out.println("love3");
            }
        }
        //对象调用
        ILike like = new LikeImpl();
        like.like();

        //静态内部类
        like = new Likes();
        like.like();

        //局部内部类
        like = new Like2();
        like.like();

        //匿名内部类
        like = new ILike() {
            @Override
            public void like() {
                System.out.println("love4");
            }
        };
        like.like();
        //lambda表达式
        like = () -> System.out.println("love4");
        like.like();

        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        map.forEach((key,val)->{
            System.out.println(key+":"+val);
        });

        DateEvent dateEvent = new DateEvent();
        dateEvent.setContext("this date event");
        System.out.println(dateEvent.getContext());

    }
    private void testEvent(EventBase eventBase){
        Class<? extends EventBase> clazz = eventBase.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }


}



/**函数式接口*/
interface ILike{
    void like();
}
class LikeImpl implements ILike{

    @Override
    public void like() {
        System.out.println("love");
    }
}

