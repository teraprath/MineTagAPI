package eu.minetag.plugin;

import org.bukkit.GameRule;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineTagPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        setDefaultGameRules();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setDefaultGameRules() {
        getServer().getWorlds().forEach(world -> {
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
        });
    }
}
