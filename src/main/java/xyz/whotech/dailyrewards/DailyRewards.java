package xyz.whotech.dailyrewards;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;
import xyz.whotech.dailyrewards.commands.ClaimCommand;
import xyz.whotech.dailyrewards.events.PlayerListener;
import xyz.whotech.dailyrewards.settings.Settings;

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

    public Economy getEcon() {
        return econ;
    }
}
