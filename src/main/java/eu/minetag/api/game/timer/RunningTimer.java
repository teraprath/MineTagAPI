package eu.minetag.api.game.timer;


import eu.minetag.api.game.GameAPI;
import eu.minetag.api.game.event.RunningCompleteEvent;
import eu.minetag.api.game.event.RunningTickEvent;
import eu.minetag.lib.game.state.GameState;
import eu.minetag.lib.game.timer.GameTimer;

public class RunningTimer extends GameTimer {

    private final GameAPI api;
    private final RunningTickEvent tickEvent;
    private final RunningCompleteEvent completeEvent;

    public RunningTimer(GameAPI api) {
        super(api.getPlugin(), api.getSettings().getRunningDuration());
        this.api = api;
        this.tickEvent = new RunningTickEvent(api);
        this.completeEvent = new RunningCompleteEvent(api);
    }

    @Override
    protected void onTick() {
        api.getPlugin().getServer().getPluginManager().callEvent(tickEvent);
    }

    @Override
    protected void onComplete() {
        api.getPlugin().getServer().getPluginManager().callEvent(completeEvent);
        api.setGameState(GameState.SHUTDOWN);
        api.getShutdownTimer().start();
    }
}
