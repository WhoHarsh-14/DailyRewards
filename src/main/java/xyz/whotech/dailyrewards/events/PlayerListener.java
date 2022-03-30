package xyz.whotech.dailyrewards.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.whotech.dailyrewards.DailyRewards;
import xyz.whotech.dailyrewards.managers.PlayerManager;
import xyz.whotech.dailyrewards.managers.RewardsManager;
import xyz.whotech.dailyrewards.settings.Settings;
import xyz.whotech.dailyrewards.utils.PlayerUtil;

public class PlayerListener implements Listener {
    private DailyRewards dailyRewards;

    public PlayerListener(DailyRewards dailyRewards){
        this.dailyRewards = dailyRewards;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if (!Settings.isAutoClaimEnabled){
            return;
        }
        Player player = event.getPlayer();
        PlayerManager manager = PlayerManager.getCache(player);
        if (PlayerUtil.getMap().get(player.getUniqueId())){
            RewardsManager.giveFinalRewards(player, dailyRewards, manager);
        }else if (!(PlayerUtil.getMap().containsKey(player.getUniqueId()))){
            RewardsManager.giveFirstRewards(player, dailyRewards);
        }
    }
}
