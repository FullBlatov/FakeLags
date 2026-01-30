package kz.fullblatov.fakelags.listeners;

import kz.fullblatov.fakelags.manager.TrollingManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        TrollingManager.getTrollings().remove(event.getPlayer());
    }
}
