package yljin.cn;

import com.google.gson.reflect.TypeToken;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.URISyntaxException;
import java.util.List;

public class Listen implements Listener {
    private final Demo plugin;

    public Listen(Demo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String userName = event.getPlayer().getName();
        plugin.getLogger().info(userName + " joined the server! :D");

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " left the server! :'(");
    }

    @EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event) throws URISyntaxException {
        plugin.getLogger().info("监听到玩家聊天");
        if (event.isCancelled()) return;
        String newMessage = null;
        newMessage = String.format("从游戏中转发的消息:[%s用户发送了:%s]", event.getPlayer().getName(), event.getMessage());
        plugin.getLogger().info(newMessage);
        plugin.myClent.send(newMessage);
    }
}