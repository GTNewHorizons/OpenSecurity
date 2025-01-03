package pcl.opensecurity.networking;

import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import pcl.opensecurity.entity.EntityEnergyBolt;

public class PacketBoltFire implements IMessage, IMessageHandler<PacketBoltFire, IMessage> {

    float yaw;
    float pitch;
    int entityId;

    public PacketBoltFire() {}

    public PacketBoltFire(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public IMessage onMessage(PacketBoltFire message, MessageContext ctx) {
        ((EntityEnergyBolt) Minecraft.getMinecraft().thePlayer.worldObj.getEntityByID(this.entityId))
                .setHeading(this.yaw, this.pitch);

        return null;
    }

    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
        this.yaw = buf.readFloat();
        this.pitch = buf.readFloat();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeFloat(this.yaw);
        buf.writeFloat(this.pitch);
    }
}
