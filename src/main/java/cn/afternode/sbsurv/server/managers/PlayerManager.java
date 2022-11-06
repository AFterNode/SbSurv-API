package cn.afternode.sbsurv.server.managers;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerManager implements Listener {
    private final List<Player> players;

    public PlayerManager() {
        players = new ArrayList<>();
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        if (!hasPlayer(player)) return;
        players.remove(player);
    }

    public void onPlayerLeave(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }
}
