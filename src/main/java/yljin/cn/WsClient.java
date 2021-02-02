package yljin.cn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

public class WsClient extends WebSocketClient {

    public WsClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }


    @Override
    public void onOpen(ServerHandshake arg0) {
        System.out.println("握手成功");

    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println("连接关闭");
    }

    @Override
    public void onError(Exception arg0) {
        System.out.println("发生错误");
    }

    @Override
    public void onMessage(String arg0) {

        JSONObject jsonObject = JSONObject.parseObject(arg0);
        String message = jsonObject.getString("message");
        String sendName = jsonObject.getString("sendName");
        String userQq = jsonObject.getString("userQq");
        String newMessage = null;
        newMessage = String.format("§c从qq群转发的消息:[qq号为%s,的用户:%s,发送了:%s]", userQq, sendName, message);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(newMessage);
            System.out.println("发送给所有玩家成功");
        }
        System.out.println("收到消息:" + newMessage);
    }
}
