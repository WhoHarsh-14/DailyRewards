package xyz.whotech.dailyrewards.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public class PlayerUtil {
    public Map<UUID, Long> newDayMap = new HashMap<>();

    public Map<UUID, Long> getMap(){
        return newDayMap;
    }
}
