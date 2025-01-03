package pcl.opensecurity.networking;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import pcl.opensecurity.OpenSecurity;
import pcl.opensecurity.tileentity.TileEntityKeypadLock;

public class HandlerKeypadButton implements IMessageHandler<PacketKeypadButton, IMessage> {

    @Override
    public IMessage onMessage(PacketKeypadButton message, MessageContext ctx) {
        short instanceID = message.id;
        int dim = message.dimension;
        int x = message.x;
        int y = message.y;
        int z = message.z;

        // BLLog.debug("Keypad packet at %d, %d, %d in dim %d", x, y, z, dim);

        World world = OpenSecurity.proxy.getWorld(dim);

        if (world != null) {
            TileEntity te = world.getTileEntity(x, y, z);

            int button = message.button;
            // BLLog.debug("Got button for button # %d", button);

            TileEntityKeypadLock tek = (TileEntityKeypadLock) te;
            tek.buttonStates[button].press(te.getWorldObj().getTotalWorldTime());
        }
        return null;
    }
}
