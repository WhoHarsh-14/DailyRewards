package xyz.whotech.dailyrewards.managers;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.mineacademy.fo.settings.YamlSectionConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager extends YamlSectionConfig {
    private static final Map<UUID, PlayerManager> cacheMap = new HashMap<>();

    @Getter
    private int days;
    @Getter
    private long timeForNextReward;


    public PlayerManager(String uuid) {
        super(String.valueOf(uuid));
        loadConfiguration(null, "data.db");
    }

    public static PlayerManager getCache(final Player player) {
        return getCache(player.getUniqueId());
    }

    public static PlayerManager getCache(final UUID uuid) {
        PlayerManager cache = cacheMap.get(uuid);

        if (cache == null) {
            cache = new PlayerManager(uuid.toString());

            cacheMap.put(uuid, cache);
        }

        return cache;
    }

    public static void clearAllData() {
        cacheMap.clear();
    }

    @Override
    protected void onLoadFinish() {
        if (isSet("Days")) {
            days = getInteger("Days");
        }
        if (isSet("Time")) {
            timeForNextReward = getLong("Time");
        }
    }

    public void setDays(int days) {
        this.days = days;
        save("Days", days);
    }

    public void setTimeForNextReward(long timeForNextReward) {
        this.timeForNextReward = timeForNextReward;
        save("Time", timeForNextReward);
    }

}
