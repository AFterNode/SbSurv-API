package cn.afternode.sbsurv.server.listener;

import cn.afternode.sbsurv.server.SbSurvServer;
import cn.afternode.sbsurv.server.utils.PayloadPacket;
import cn.afternode.sbsurv.server.utils.wrappers.CPacketCustomPayload;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {
    private final List<PayloadListener> payloadListeners;

    public ListenerManager() {
        payloadListeners = new ArrayList<>();

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(SbSurvServer.INSTANCE, ListenerPriority.NORMAL, PacketType.Play.Client.CUSTOM_PAYLOAD) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                CPacketCustomPayload pkt = new CPacketCustomPayload(event.getPacket());
                PayloadPacket pp = new PayloadPacket(pkt, event.getPlayer());
                callPayload(pp);
            }
        });
    }

    public void registerPayloadListener(PayloadListener listener) {
        if (payloadListeners.contains(listener)) return;
        payloadListeners.add(listener);
    }
    private void callPayload(PayloadPacket packet) {
        for (PayloadListener listener : payloadListeners) {
            try {
                new Thread(() -> {
                    listener.onPacket(packet);
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
