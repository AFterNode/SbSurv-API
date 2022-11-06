package cn.afternode.sbsurv.server.utils.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public class SPacketCustomPayload extends AbstractPacket{
    public static final PacketType TYPE = PacketType.Play.Server.CUSTOM_PAYLOAD;

    public SPacketCustomPayload() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public SPacketCustomPayload(PacketContainer packet) {
        super(packet, TYPE);
    }

    /**
     * Retrieve name of the "channel" used to send the data..
     * @return The current Channel
     */
    public String getChannel() {
        return handle.getStrings().read(0);
    }

    /**
     * Set name of the "channel" used to send the data..
     * @param value - new value.
     */
    public void setChannel(String value) {
        handle.getStrings().write(0, value);
    }

    /**
     * Retrieve the custom data that sent in this message.
     * @return The current data
     */
    public byte[] getData() {
        return handle.getByteArrays().read(0);
    }

    /**
     * Set the custom data that is sent.
     * @param value - new value.
     */
    public void setData(byte[] value) {
        handle.getByteArrays().write(0, value);
    }
}
