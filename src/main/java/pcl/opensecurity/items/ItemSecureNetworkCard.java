package pcl.opensecurity.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import pcl.opensecurity.drivers.SecureNetworkCardDriver;

public class ItemSecureNetworkCard extends Item implements li.cil.oc.api.driver.Item, HostAware, EnvironmentAware {

    public ItemSecureNetworkCard() {
        super();
        setUnlocalizedName("secureNetworkCard");
        setTextureName("opensecurity:secureNetworkCard");
    }

    @Override
    public boolean worksWith(ItemStack stack) {
        return stack.getItem().equals(this);
    }

    @Override
    public ManagedEnvironment createEnvironment(ItemStack arg0, li.cil.oc.api.network.EnvironmentHost arg1) {
        return new SecureNetworkCardDriver(arg1);
    }

    @Override
    public String slot(ItemStack stack) {
        return Slot.Card;
    }

    @Override
    public int tier(ItemStack stack) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public NBTTagCompound dataTag(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        final NBTTagCompound nbt = stack.getTagCompound();
        if (!nbt.hasKey("oc:data")) {
            nbt.setTag("oc:data", new NBTTagCompound());
        }
        return nbt.getCompoundTag("oc:data");
    }

    @Override
    public Class<? extends Environment> providedEnvironment(ItemStack stack) {
        // TODO Auto-generated method stub
        return SecureNetworkCardDriver.class;
    }

    @Override
    public boolean worksWith(ItemStack arg0, Class<? extends li.cil.oc.api.network.EnvironmentHost> arg1) {
        // TODO Auto-generated method stub
        boolean works = worksWith(arg0);
        return works;
    }
}
