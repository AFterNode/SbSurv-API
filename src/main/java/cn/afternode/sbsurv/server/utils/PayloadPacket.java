package cn.afternode.sbsurv.server.utils;

import cn.afternode.sbsurv.server.utils.wrappers.CPacketCustomPayload;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

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

    public HashMap<String, String> getKeyPairs() {
        HashMap<String, String> map = new HashMap<>();

        String[] strs = getString().split("\\|");
        for (String str: strs) {
            if (str.equals("SbSurv")) continue;
            String[] s2 = str.split(" ");
            map.put(s2[0], s2[1]);
        }
        return map;
    }
}
