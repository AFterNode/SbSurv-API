package cn.afternode.sbsurv.server.utils;

import cn.afternode.sbsurv.server.utils.wrappers.CPacketCustomPayload;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;

public class PayloadPacket {
    private final CPacketCustomPayload packet;
    private final Player player;

    public PayloadPacket(CPacketCustomPayload pkt, Player player) {
        packet = pkt;
        this.player = player;
    }

    public String getString() {
        return new String(packet.getData(), StandardCharsets.UTF_8);
    }

    public Player getPlayer() {
        return player;
    }
}
