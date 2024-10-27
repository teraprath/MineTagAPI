package eu.minetag.api.game.listener;

import eu.minetag.api.game.GameAPI;
import eu.minetag.api.game.event.GameQuitEvent;
import eu.minetag.lib.game.state.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final GameAPI api;

    public PlayerQuitListener(GameAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        e.quitMessage(null);

        if (api.getGameState().equals(GameState.WAITING)) {

            int online = api.getPlugin().getServer().getOnlinePlayers().size() - 1;

            if (online < api.getSettings().getMinPlayers() && api.getWaitingTimer().isRunning()) {
                api.getWaitingTimer().cancel();
            }
        }

        api.getPlugin().getServer().getPluginManager().callEvent(new GameQuitEvent(e.getPlayer(), api));
    }

}
