package cn.afternode.sbsurv.server;

import cn.afternode.sbsurv.common.protocol.MessageType;
import cn.afternode.sbsurv.common.protocol.ProtocolAdapter;
import cn.afternode.sbsurv.common.protocol.messages.*;
import cn.afternode.sbsurv.server.listener.ListenerManager;
import cn.afternode.sbsurv.server.listener.PayloadListener;
import cn.afternode.sbsurv.server.managers.PlayerManager;
import cn.afternode.sbsurv.server.utils.PacketBuilder;
import cn.afternode.sbsurv.server.utils.wrappers.SPacketCustomPayload;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;

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

    public Message readMessage(byte[] data) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String msg = new String(data, StandardCharsets.UTF_8);
        MessageType type = ProtocolAdapter.getType(msg);
        if (type == MessageType.Request) {
            return ProtocolAdapter.fromString(msg, RequestMessage.class);
        } else if (type == MessageType.Response) {
            return ProtocolAdapter.fromString(msg, ResponseMessage.class);
        }  else if (type == MessageType.Order) {
            return ProtocolAdapter.fromString(msg, OrderMessage.class);
        } else if (type == MessageType.Query) {
            return ProtocolAdapter.fromString(msg, QueryMessage.class);
        } else {
            throw new InvalidParameterException();
        }
    }
}
