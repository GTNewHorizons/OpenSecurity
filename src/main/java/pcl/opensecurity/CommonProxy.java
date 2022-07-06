/**
 *
 */
package pcl.opensecurity;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pcl.opensecurity.containers.CardWriterContainer;
import pcl.opensecurity.tileentity.TileEntityCardWriter;
import pcl.opensecurity.tileentity.TileEntityRFIDReader;

/**
 * @author Caitlyn
 *
 */
public class CommonProxy implements IGuiHandler {

    public void registerRenderers() {}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te != null && te instanceof TileEntityRFIDReader) {
            TileEntityCardWriter icte = (TileEntityCardWriter) te;
            return new CardWriterContainer(player.inventory, icte);
        } else {
            return null;
        }
    }

    public World getWorld(int dimId) {
        // overridden separately for client and server.
        return null;
    }

    public void registerSounds() {
        // TODO Auto-generated method stub

    }
}
