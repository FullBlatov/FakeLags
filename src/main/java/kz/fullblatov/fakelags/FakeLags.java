package kz.fullblatov.fakelags;

import com.github.retrooper.packetevents.PacketEvents;
import kz.fullblatov.fakelags.commands.TrollingCommand;
import kz.fullblatov.fakelags.listeners.PacketListener;
import kz.fullblatov.fakelags.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FakeLags extends JavaPlugin {

    private final PacketListener packetListener = new PacketListener();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Config.load(getConfig());

        getCommand("trolling").setExecutor(new TrollingCommand());
        getCommand("trolling").setTabCompleter(new TrollingCommand());

        PacketEvents.getAPI().getEventManager().registerListener(packetListener);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().getEventManager().unregisterListener(packetListener);
    }
}
