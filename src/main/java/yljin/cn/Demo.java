package yljin.cn;

import org.bukkit.plugin.java.JavaPlugin;
import org.java_websocket.WebSocket;
import org.bukkit.plugin.PluginManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class Demo extends JavaPlugin {
    private final Listen playerListener = new Listen(this);
    // 根据实际websocket地址更改
    private static String myWsUr = "ws://127.0.0.1:20001";
    public WsClient myClent;

    @Override
    public void onEnable() {
        getLogger().info("我的第一个插件已经加载.");
        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        try {
            WsClient myClient = conn();
            // 判断是否连接成功，未成功后面发送消息时会报错
            while (!myClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                System.out.println("连接中···请稍后");
                Thread.sleep(1000);
            }
            myClient.send("Minecraft游戏插件已经启动......");
            getLogger().info("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDisable() {
        myClent.close();
        myClent = null;
        getLogger().info("我的第一个插件已经退出.");
    }

    public WsClient conn() throws URISyntaxException {
        if (myClent != null) {
            return myClent;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("x-client-role","minecraft");
        map.put("authorization","wefewfadew1ew564f568ew!!@#");
        WsClient myClient = new WsClient(new URI(myWsUr),map);
        myClient.connect();
        myClent = myClient;
        return myClent;
    }


}
