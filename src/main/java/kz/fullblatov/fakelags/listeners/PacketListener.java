package kz.fullblatov.fakelags.listeners;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.event.ProtocolPacketEvent;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import kz.fullblatov.fakelags.manager.TrollingManager;
import kz.fullblatov.fakelags.trolling.AbstractTrolling;
import org.bukkit.entity.Player;

import java.util.HashSet;

public final class PacketListener extends PacketListenerAbstract {

    @Override
    public void onPacketReceive(PacketReceiveEvent e) {
        if (eventCheck(e)) return;

        final Player player = e.getPlayer();
        final HashSet<AbstractTrolling> playerTrolling = TrollingManager.getTrollings().get(player);

        if (playerTrolling != null) {
            for (AbstractTrolling trolling : playerTrolling) trolling.packetReceive(e);
        }
    }

    @Override
    public void onPacketSend(PacketSendEvent e) {
        if (eventCheck(e)) return;

        final Player player = e.getPlayer();
        final HashSet<AbstractTrolling> playerTrolling = TrollingManager.getTrollings().get(player);

        if (playerTrolling != null) {
            for (AbstractTrolling trolling : playerTrolling)
                trolling.packetSend(e);
        }
    }

    private boolean eventCheck(ProtocolPacketEvent event) {
        if (event.getConnectionState() != ConnectionState.PLAY) {
            return true;
        }

        return event.getPlayer() == null;
    }

}
