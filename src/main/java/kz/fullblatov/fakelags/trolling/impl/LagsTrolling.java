package kz.fullblatov.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import kz.fullblatov.fakelags.Config;
import kz.fullblatov.fakelags.trolling.AbstractTrolling;
import kz.fullblatov.fakelags.trolling.TrollingType;

public class LagsTrolling extends AbstractTrolling {

    public LagsTrolling() {
        super(TrollingType.LAGS);
    }

    @Override
    public void packetReceive(PacketReceiveEvent event) {
        if (Math.random() < Config.packetCancelChance / 100.0) {
            event.setCancelled(true);
        }
    }

    @Override
    public void packetSend(PacketSendEvent event) {

    }
}
