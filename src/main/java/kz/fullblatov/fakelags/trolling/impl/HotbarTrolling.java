package kz.fullblatov.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerHeldItemChange;
import kz.fullblatov.fakelags.trolling.AbstractTrolling;
import kz.fullblatov.fakelags.trolling.TrollingType;

import java.util.concurrent.ThreadLocalRandom;

public class HotbarTrolling extends AbstractTrolling {

    public HotbarTrolling() {
        super(TrollingType.HOTBAR);
    }

    @Override
    public void packetReceive(PacketReceiveEvent event) {
        final User user = event.getUser();
        final WrapperPlayServerHeldItemChange packet = new WrapperPlayServerHeldItemChange(ThreadLocalRandom.current().nextInt(9));
        user.sendPacket(packet);
    }

    @Override
    public void packetSend(PacketSendEvent event) {

    }
}
