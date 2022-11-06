package cn.afternode.sbsurv.server.listener;

import cn.afternode.sbsurv.server.utils.PayloadPacket;

public interface PayloadListener {
    /**
     * 监听Payload发包
     * 多线程，禁止直接调用Bukkit.*
     * @param packet Payload发包
     */
    void onPacket(PayloadPacket packet);
}
