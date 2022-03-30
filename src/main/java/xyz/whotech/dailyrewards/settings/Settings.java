package xyz.whotech.dailyrewards.settings;

import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {
    public static String command;
    public static int economyValue;
    public static boolean isEconomyEnabled;
    public static boolean isAutoClaimEnabled;
    public static String NO_REWARD;
    public static String AUTO_CLAIM_ENABLED;
    public static String GIVING_REWARD;
    @Override
    protected int getConfigVersion() {
        return 1;
    }
    private static void init(){
        pathPrefix("Rewards");
        command = getString("Extra_Commands");
        economyValue = getInteger("Base_value");
        isEconomyEnabled = getBoolean("Economy");
        pathPrefix("Features");
        isAutoClaimEnabled = getBoolean("auto_claim");

        // Messages
        pathPrefix("Messages");
        NO_REWARD = getString("No_Rewards");
        GIVING_REWARD = getString("Reward");
        AUTO_CLAIM_ENABLED = getString("Auto_Claim_Enabled");
    }
}
