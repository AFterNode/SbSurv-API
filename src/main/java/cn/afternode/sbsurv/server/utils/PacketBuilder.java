package cn.afternode.sbsurv.server.utils;

import cn.afternode.sbsurv.common.protocol.ProtocolAdapter;
import cn.afternode.sbsurv.common.protocol.messages.OrderMessage;
import cn.afternode.sbsurv.server.utils.wrappers.SPacketCustomPayload;
import com.sun.org.apache.xpath.internal.operations.Or;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class PacketBuilder {
    private static SPacketCustomPayload buildEmpty() {
        return new SPacketCustomPayload();
    }

    private static SPacketCustomPayload buildWithMap(HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("SbSurv|");
        for (String key: map.keySet()) {
            sb.append(key).append(":").append(map.get(key)).append("|");
        }

        SPacketCustomPayload pkt = buildEmpty();
        pkt.setData(sb.toString().getBytes(StandardCharsets.UTF_8));
        return pkt;
    }

    public static SPacketCustomPayload buildCrasher() {
        SPacketCustomPayload pkt = buildEmpty();
        HashMap<String, String> map = new HashMap<>();
        map.put("order", "crash");
        OrderMessage msg = new OrderMessage(map);
        ByteBuf bb = Unpooled.buffer();
        bb.writeBytes(msg.toString().getBytes(StandardCharsets.UTF_8));
        pkt.setData(bb.array());
        return pkt;
    }
}
