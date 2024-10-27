package eu.minetag.api.game.event;

import eu.minetag.api.game.GameAPI;
import eu.minetag.api.game.timer.RunningTimer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class RunningTickEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final GameAPI api;

    public RunningTickEvent(final GameAPI api) {
        this.api = api;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() { return HANDLER_LIST; }

    public RunningTimer getTimer() { return this.api.getRunningTimer(); }

    public GameAPI getGame() { return this.api; }

}
