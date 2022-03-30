package xyz.whotech.dailyrewards.timers;

import org.bukkit.scheduler.BukkitRunnable;
import xyz.whotech.dailyrewards.utils.PlayerUtil;

public class DayTimer extends BukkitRunnable {

    @Override
    public void run() {
        PlayerUtil.getMap().forEach((uuid, isNewDay) -> {
            isNewDay = true;
        });
    }
}
