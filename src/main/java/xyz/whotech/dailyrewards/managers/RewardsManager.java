package xyz.whotech.dailyrewards.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.fo.Common;
import xyz.whotech.dailyrewards.DailyRewards;
import xyz.whotech.dailyrewards.settings.Settings;
import xyz.whotech.dailyrewards.utils.PlayerUtil;

public class RewardsManager {
    public static void giveRewards(@NotNull Player player, DailyRewards dailyRewards){
        if (Settings.isEconomyEnabled){
            int baseAmount = Settings.economyValue;
            PlayerManager manager = PlayerManager.getCache(player);
            dailyRewards.getEcon().depositPlayer(player, baseAmount * manager.getDays());
        }
        String command = Settings.command.replace("{player}", player.getName());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public static void giveFinalRewards(Player player, DailyRewards dailyRewards, PlayerManager manager){
        String message = Settings.GIVING_REWARD.replace("{days}", String.valueOf(manager.getDays()));
        Common.tell(player, message);
        RewardsManager.giveRewards(player,dailyRewards);
        manager.setDays(manager.getDays() + 1);
        PlayerUtil.getMap().replace(player.getUniqueId(), System.currentTimeMillis() + 86400000);
    }
    public static void giveFirstRewards(Player player, DailyRewards dailyRewards){
        String message = Settings.FIRST_TIME_REWARD;
        Common.tell(player, message);
        RewardsManager.giveRewards(player,dailyRewards);
        PlayerManager manager = new PlayerManager(player.getUniqueId().toString());
        manager.setDays(1);
        PlayerUtil.getMap().put(player.getUniqueId(), System.currentTimeMillis() + 86400000 );
    }
}
