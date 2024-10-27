package eu.minetag.api.game;

import eu.minetag.api.game.event.GameRetireEvent;
import eu.minetag.api.game.event.GameStateChangeEvent;
import eu.minetag.api.game.listener.GamePingListener;
import eu.minetag.api.game.listener.PlayerJoinListener;
import eu.minetag.api.game.listener.PlayerQuitListener;
import eu.minetag.api.game.listener.WaitingListener;
import eu.minetag.api.game.timer.RunningTimer;
import eu.minetag.api.game.timer.ShutdownTimer;
import eu.minetag.api.game.timer.WaitingTimer;
import eu.minetag.lib.game.state.GameState;
import eu.minetag.lib.game.state.GameStateManager;
import eu.minetag.lib.game.team.GameTeamManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class GameAPI extends GameStateManager {

    private final JavaPlugin plugin;
    private final GameSettings settings;
    private final GameTeamManager teamManager;
    private final WaitingTimer waitingTimer;
    private final RunningTimer runningTimer;
    private final ShutdownTimer shutdownTimer;
    private final List<Player> alive;

    public GameAPI(@Nonnull GameSettings settings, @Nonnull JavaPlugin plugin) {
        this.plugin = plugin;
        this.settings = settings;
        this.teamManager = new GameTeamManager();
        this.waitingTimer = new WaitingTimer(this);
        this.runningTimer = new RunningTimer(this);
        this.shutdownTimer = new ShutdownTimer(this);
        this.alive = new ArrayList<>();
    }

    public GameAPI init() {
        registerEvents();
        return this;
    }

    private void registerEvents() {
        final PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new GamePingListener(this), this.plugin);
        pm.registerEvents(new PlayerJoinListener(this), this.plugin);
        pm.registerEvents(new PlayerQuitListener(this), this.plugin);
        pm.registerEvents(new WaitingListener(this), this.plugin);
    }

    @Override
    public void onChange(GameState gameState) {
        if (gameState.equals(GameState.RUNNING)) {
            this.alive.addAll(plugin.getServer().getOnlinePlayers());
        }
        plugin.getServer().getPluginManager().callEvent(new GameStateChangeEvent(this));
    }

    public GameSettings getSettings() {
        return settings;
    }

    public GameTeamManager getTeamManager() {
        return teamManager;
    }

    public WaitingTimer getWaitingTimer() {
        return waitingTimer;
    }

    public RunningTimer getRunningTimer() {
        return runningTimer;
    }

    public ShutdownTimer getShutdownTimer() {
        return shutdownTimer;
    }

    public List<Player> getAlive() {
        return this.alive;
    }

    public void retire(@Nonnull Player player) {
        this.alive.remove(player);
        player.setGameMode(GameMode.SPECTATOR);
        plugin.getServer().getPluginManager().callEvent(new GameRetireEvent(player, this));
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }
}
