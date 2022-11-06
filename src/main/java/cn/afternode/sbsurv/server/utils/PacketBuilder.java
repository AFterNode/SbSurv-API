package cn.afternode.sbsurv.server.utils;

import cn.afternode.sbsurv.server.utils.wrappers.SPacketCustomPayload;

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
        pkt.setData("SbSurv|Crash".getBytes(StandardCharsets.UTF_8));
        return pkt;
    }
}
