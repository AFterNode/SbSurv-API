package cn.afternode.sbsurv.server;

import cn.afternode.sbsurv.server.listener.ListenerManager;
import cn.afternode.sbsurv.server.listener.PayloadListener;
import cn.afternode.sbsurv.server.managers.PlayerManager;
import cn.afternode.sbsurv.server.utils.PacketBuilder;
import cn.afternode.sbsurv.server.utils.wrappers.SPacketCustomPayload;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class SbSurvAPI {
    private final PlayerManager pm;
    private final ListenerManager lm;
    private final ProtocolManager prm;

    public SbSurvAPI(PlayerManager pm, ListenerManager lm) {
        this.pm = pm;
        this.lm = lm;
        prm = ProtocolLibrary.getProtocolManager();
    }

    public void registerPayloadListener(PayloadListener listener) {
        lm.registerPayloadListener(listener);
    }

    public boolean isSbSurvPlayer(Player player) {
        return pm.hasPlayer(player);
    }

    public void crashClient(Player player) throws InvocationTargetException {
        SPacketCustomPayload pkt = PacketBuilder.buildCrasher();
        prm.sendServerPacket(player, pkt.getHandle());
    }
}
