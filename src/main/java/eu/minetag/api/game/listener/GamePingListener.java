package eu.minetag.api.game.listener;

import eu.minetag.api.game.GameAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class GamePingListener implements Listener {

    private final GameAPI api;

    public GamePingListener(GameAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {
        e.motd(Component.text(api.getGameState().toString()));
    }

}
