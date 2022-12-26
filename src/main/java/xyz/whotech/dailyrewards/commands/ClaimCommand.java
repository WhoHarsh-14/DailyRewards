package xyz.whotech.dailyrewards.commands;

import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;
import xyz.whotech.dailyrewards.DailyRewards;
import xyz.whotech.dailyrewards.managers.PlayerManager;
import xyz.whotech.dailyrewards.managers.RewardsManager;
import xyz.whotech.dailyrewards.settings.Settings;
import xyz.whotech.dailyrewards.utils.PlayerUtil;

public class ClaimCommand extends SimpleCommand {
    private DailyRewards dailyRewards;
    public ClaimCommand(DailyRewards dailyRewards) {
        super("dr");
        this.dailyRewards = dailyRewards;
    }

    @Override
    protected void onCommand() {
        checkConsole();
        final Player player = getPlayer();
        PlayerManager manager = PlayerManager.getCache(player);
        if (Settings.isAutoClaimEnabled){
            Common.tell(player, Settings.AUTO_CLAIM_ENABLED);
        }else {
            if (PlayerUtil.getMap().containsKey(player.getUniqueId())){
                if (PlayerUtil.getMap().get(player.getUniqueId()) >= System.currentTimeMillis()){
                    RewardsManager.giveFinalRewards(player, dailyRewards, manager);
                }else {
                    Common.tell(player, Settings.NO_REWARD);
                }
            }else {
                RewardsManager.giveFirstRewards(player, dailyRewards);
            }
        }
    }
}
