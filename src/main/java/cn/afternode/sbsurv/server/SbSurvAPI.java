package cn.afternode.sbsurv.server;

import cn.afternode.sbsurv.server.listener.ListenerManager;
import cn.afternode.sbsurv.server.listener.PayloadListener;
import cn.afternode.sbsurv.server.managers.PlayerManager;
import org.bukkit.entity.Player;

public class SbSurvAPI {
    private final PlayerManager pm;
    private final ListenerManager lm;

    public SbSurvAPI(PlayerManager pm, ListenerManager lm) {
        this.pm = pm;
        this.lm = lm;
    }

    public void registerPayloadListener(PayloadListener listener) {
        lm.registerPayloadListener(listener);
    }

    public boolean isSbSurvPlayer(Player player) {
        return pm.hasPlayer(player);
    }
}
