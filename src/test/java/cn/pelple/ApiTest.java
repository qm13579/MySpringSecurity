package cn.pelple;
import org.junit.Test;
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

