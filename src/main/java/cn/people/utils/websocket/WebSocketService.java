package cn.people.utils.websocket;

import com.sun.star.reflection.XPublished;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author : FENGZHI
 * create at:  2020/5/22  下午10:07
 * @description:
 */
@Slf4j
@ServerEndpoint(value = "/websocket/{sid}")
@Component
public class WebSocketService {

    private static int onLineCount = 0;

    /**用来存放每一个客户端的socket**/
    private static CopyOnWriteArrayList<WebSocketService> webSocketSet = new CopyOnWriteArrayList<>();

    /**创建map用来存放socket*/
    private static ConcurrentHashMap<String,WebSocketService> webSocketMap = new ConcurrentHashMap<>();

    /**与某个客户端的会话，通过session发送数据**/
    private Session session;
    /**接收sid*/
    private String sid = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){
        this.session = session;
        this.sid = sid;
        if (webSocketMap.containsKey(sid)){
            webSocketMap.remove(sid);
            webSocketMap.put(sid,this);
        }else {
            webSocketMap.put(sid,this);
        }
        addOnline();
        log.info("有新窗口监听{}。当前在线人数{}",sid,getOnLineCount());
    }

    /**收到客户端消息后调用消息
     * 可以把消息保存到数据库、redis*/
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("收到来自窗口{}消息：{}",sid,message);

        webSocketMap.get(sid).sendMessage("已收到消息");

    }

    @OnClose
    public void  onClose(){
        webSocketMap.remove(sid);
        subOnlineCount();
        log.info("有一链接关闭，当前在线人数为{}",getOnLineCount());
    }


    /**服务端主动推送消息*/
    public void sendMessage(String message)  {

        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**自定义群发消息*/
    public static void sendInfo(String message,@PathParam("sid") String sid){
        log.info("消息推送{},内容为{}",sid,message);
        webSocketSet.forEach(socket -> {
            socket.sendMessage(message);
        });
    }
    public static synchronized void addOnline(){
        WebSocketService.onLineCount++;
    }
    public static synchronized int getOnLineCount(){
        return onLineCount;
    }
    public static synchronized void subOnlineCount(){
        WebSocketService.onLineCount--;
    }

}
