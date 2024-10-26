package eu.minetag.api.game.event;

import eu.minetag.api.game.GameAPI;
import eu.minetag.lib.game.state.GameState;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GameStateChangeEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final GameAPI api;

    public GameStateChangeEvent(final GameAPI api) {
        this.api = api;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() { return HANDLER_LIST; }

    public GameState getGameState() { return this.api.getGameState(); }

    public GameAPI getGame() { return this.api; }

}
