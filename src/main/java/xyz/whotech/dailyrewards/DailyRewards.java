package xyz.whotech.dailyrewards;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;
import xyz.whotech.dailyrewards.commands.ClaimCommand;
import xyz.whotech.dailyrewards.events.PlayerListener;
import xyz.whotech.dailyrewards.settings.Settings;
import xyz.whotech.dailyrewards.timers.DayTimer;

import java.util.Arrays;
import java.util.List;

public final class DailyRewards extends SimplePlugin {
    Economy econ = null;

    @Override
    protected void onPluginStart() {
        if (!setupEconomy() && Settings.isEconomyEnabled) {
            Common.log("Economy Not Found ( Vault ) ");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        registerEvents(new PlayerListener(this));
        registerCommand(new ClaimCommand(this));
        DayTimer timer = new DayTimer();
        timer.runTaskTimer(this, 0, 20 * 3600 * 24);
        Common.log("ＷｈｏＴｅｃｈ || Daily Rewards Started Successfully!");
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public List<Class<? extends YamlStaticConfig>> getSettings() {
        return Arrays.asList(Settings.class);

    }

    public Economy getEcon() {
        return econ;
    }
}
