package com.ycjcjy.gene.common.webSocket;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author 0neBean
 * '@ServerEndpoint' ServerEndpoint注解
 * 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个'websocket'服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket/{uid}")
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;
    private static int onlineCount = 0 ;
    private String currentUserId;

    /**
     * 连接建立成功调用的方法
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session,@PathParam(value="uid") String uid){
        currentUserId = uid;
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //在线数加
        addOnlineCount();
        logger.info("有新连接加入！当前在线人数为",getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        //从set中删除
        webSocketSet.remove(this);
        //在线数减
        subOnlineCount();
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息:" + message);
        JSONObject json = new JSONObject();
        json.put("title","哈哈哈");
        json.put("context","老子是后台发来的捷报");
        try {
            sendMessage(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     * @param session socket会话
     * @param error socket异常
     */
    @OnError
    public void onError(Session session, Throwable error){
        logger.info("发生错误");
        error.printStackTrace();
    }

    /**
     * 给指定用户发送消息
     * @param message json消息
     * @param userId 用户ID
     * @return Boolean 发送是否成功
     * @throws IOException 抛出IO异常
     */
    public static Boolean sendMessageToUser(String code,String message,String userId) throws IOException{
        System.out.println(userId);
        for(WebSocketController item: webSocketSet){
            System.out.println("==="+ item.currentUserId);
            if (item.currentUserId.equals(userId) ){
                JSONObject json = new JSONObject();
                json.put("code",code);
                json.put("message",message);
                item.session.getBasicRemote().sendText(json.toString());
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * 给指定用户发送消息(点单系统，内部包含json)
     * @param object json消息
     * @param userId 用户ID
     * @return Boolean 发送是否成功
     * @throws IOException 抛出IO异常
     */
    public static Boolean sendMessageToUserJson(String code,Object object,String userId) throws IOException{
        System.out.println(userId);

        for(WebSocketController item: webSocketSet){
            System.out.println("==="+ item.currentUserId);
            if (item.currentUserId.equals(userId) ){
                JSONObject json = new JSONObject();
                json.put("code",code);
                json.put("message",object);
                System.out.println("----------------json:"+json.toJSONString());
                item.session.getBasicRemote().sendText(json.toJSONString());
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * 发送消息方法
     * @param message json 消息
     * @throws IOException 抛出异常
     */
    private void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发消息
     * @param message json 消息
     * @throws IOException 抛出异常
     */
    public static void sendMessageToEveryOne(String message) throws IOException{
        for(WebSocketController item: webSocketSet){
            try {
                item.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 获得在线数量
     * @return int
     */
    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 增加一个在线人数
     */
    private static synchronized void addOnlineCount() {
        WebSocketController.onlineCount++;
    }

    /**
     * 减少一个在线人数
     */
    private static synchronized void subOnlineCount() {
        WebSocketController.onlineCount--;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}