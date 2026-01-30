package kz.fullblatov.fakelags;

import lombok.experimental.UtilityClass;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;

@UtilityClass
public class Config {

    public void load(FileConfiguration file) {
        final ConfigurationSection messagesSection = file.getConfigurationSection("messages");
        if (messagesSection == null) {
            throw new IllegalStateException("Section messages is null");
        }
        parseMessages(messagesSection);
        packetCancelChance = file.getInt("packet_cancel_chance");
        adminPermission = new Permission(file.getString("admin_permission"));
    }

    private static void parseMessages(ConfigurationSection section) {
        Messages.help = section.getString("help");
        Messages.trollingEnable = section.getString("trolling_enable");
        Messages.trollingDisable = section.getString("trolling_disable");
        Messages.playerNotFound = section.getString("player_not_found");
        Messages.trollingNotFound = section.getString("trolling_not_found");
        Messages.noPermission = section.getString("no_permission");
    }

    public static int packetCancelChance;
    public static Permission adminPermission;

    @UtilityClass
    public static class Messages {
        public static String help;
        public static String trollingEnable;
        public static String trollingDisable;
        public static String playerNotFound;
        public static String trollingNotFound;
        public static String noPermission;
    }
}
