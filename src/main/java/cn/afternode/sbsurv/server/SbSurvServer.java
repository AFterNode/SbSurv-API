package cn.afternode.sbsurv.server;

import cn.afternode.sbsurv.server.listener.ListenerManager;
import cn.afternode.sbsurv.server.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SbSurvServer extends JavaPlugin {
    public static SbSurvServer INSTANCE;

    /**
     * Do not use this
     */
    public static PlayerManager playerManager;

    /**
     * Do not use this
     */
    public static ListenerManager listenerManager;

    /**
     * 获取API
     */
    public static SbSurvAPI api;

    private Logger logger;

    public static SbSurvAPI getAPI() {
        return api;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        logger = getLogger();
        try {
            logger.info("加载玩家管理器");
            playerManager = new PlayerManager();

            logger.info("加载监听器管理器");
            listenerManager = new ListenerManager();

            logger.info("加载API");
            api = new SbSurvAPI(playerManager, listenerManager);
        } catch (Exception e) {
            logger.warning("加载失败");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
