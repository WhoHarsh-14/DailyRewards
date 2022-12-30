package xyz.whotech.dailyrewards.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.whotech.dailyrewards.DailyRewards;
import xyz.whotech.dailyrewards.managers.PlayerManager;
import xyz.whotech.dailyrewards.managers.RewardsManager;
import xyz.whotech.dailyrewards.settings.Settings;

public class PlayerListener implements Listener {
    private DailyRewards dailyRewards;

    public PlayerListener(DailyRewards dailyRewards) {
        this.dailyRewards = dailyRewards;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!Settings.isAutoClaimEnabled) {
            return;
        }
        final Player player = event.getPlayer();
        PlayerManager manager = PlayerManager.getCache(player);
        if (PlayerManager.getCache(player).getDays() >= 1) {
            RewardsManager.giveFirstRewards(player, dailyRewards);
            return;
        }
        if (PlayerManager.getCache(player).getTimeForNextReward() <= System.currentTimeMillis()) {
            RewardsManager.giveFinalRewards(player, dailyRewards, manager);
        }
    }
}
