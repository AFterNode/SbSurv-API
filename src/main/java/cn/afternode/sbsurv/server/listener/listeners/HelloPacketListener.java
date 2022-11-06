package cn.afternode.sbsurv.server.listener.listeners;

import cn.afternode.sbsurv.server.SbSurvServer;
import cn.afternode.sbsurv.server.listener.PayloadListener;
import cn.afternode.sbsurv.server.utils.PayloadPacket;

public class HelloPacketListener implements PayloadListener {
    @Override
    public void onPacket(PayloadPacket packet) {
        if (packet.getString().equals("SbSurv|Hello")) {
            SbSurvServer.playerManager.addPlayer(packet.getPlayer());
        }
    }
}
