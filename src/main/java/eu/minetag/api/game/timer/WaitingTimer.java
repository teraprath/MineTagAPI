package eu.minetag.api.game.timer;


import eu.minetag.api.game.GameAPI;
import eu.minetag.api.game.event.WaitingCompleteEvent;
import eu.minetag.api.game.event.WaitingTickEvent;
import eu.minetag.lib.game.state.GameState;
import eu.minetag.lib.game.timer.GameTimer;

public class WaitingTimer extends GameTimer {

    private final GameAPI api;
    private final WaitingTickEvent tickEvent;
    private final WaitingCompleteEvent completeEvent;

    public WaitingTimer(GameAPI api) {
        super(api.getPlugin(), api.getSettings().getWaitingDuration());
        this.api = api;
        this.tickEvent = new WaitingTickEvent(api);
        this.completeEvent = new WaitingCompleteEvent(api);
    }

    @Override
    protected void onTick() {
        api.getPlugin().getServer().getPluginManager().callEvent(tickEvent);
    }

    @Override
    protected void onComplete() {
        api.getPlugin().getServer().getPluginManager().callEvent(completeEvent);
        api.setGameState(GameState.RUNNING);
        api.getRunningTimer().start();
    }
}
