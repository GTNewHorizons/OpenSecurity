/**
 *
 */
package pcl.opensecurity;

import java.util.concurrent.Callable;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import li.cil.oc.api.fs.FileSystem;
import pcl.opensecurity.blocks.BlockAlarm;
import pcl.opensecurity.blocks.BlockBiometricReader;
import pcl.opensecurity.blocks.BlockCardWriter;
import pcl.opensecurity.blocks.BlockData;
import pcl.opensecurity.blocks.BlockDoorController;
import pcl.opensecurity.blocks.BlockEnergyTurret;
import pcl.opensecurity.blocks.BlockEntityDetector;
import pcl.opensecurity.blocks.BlockKVM;
import pcl.opensecurity.blocks.BlockKeypadLock;
import pcl.opensecurity.blocks.BlockMagReader;
import pcl.opensecurity.blocks.BlockRFIDReader;
import pcl.opensecurity.blocks.BlockSecurityDoor;
import pcl.opensecurity.blocks.BlockSecurityDoorPrivate;
import pcl.opensecurity.blocks.BlockSwitchableHub;
import pcl.opensecurity.client.CreativeTab;
import pcl.opensecurity.drivers.RFIDReaderCardDriver;
import pcl.opensecurity.entity.EntityEnergyBolt;
import pcl.opensecurity.items.ItemCooldownUpgrade;
import pcl.opensecurity.items.ItemDamageUpgrade;
import pcl.opensecurity.items.ItemEnergyUpgrade;
import pcl.opensecurity.items.ItemMagCard;
import pcl.opensecurity.items.ItemMovementUpgrade;
import pcl.opensecurity.items.ItemRFIDCard;
import pcl.opensecurity.items.ItemRFIDReaderCard;
import pcl.opensecurity.items.ItemSecureNetworkCard;
import pcl.opensecurity.items.ItemSecurityDoor;
import pcl.opensecurity.items.ItemSecurityDoorPrivate;
import pcl.opensecurity.tileentity.TileEntityAlarm;
import pcl.opensecurity.tileentity.TileEntityBiometricReader;
import pcl.opensecurity.tileentity.TileEntityCardWriter;
import pcl.opensecurity.tileentity.TileEntityDataBlock;
import pcl.opensecurity.tileentity.TileEntityDisplayPanel;
import pcl.opensecurity.tileentity.TileEntityDoorController;
import pcl.opensecurity.tileentity.TileEntityEnergyTurret;
import pcl.opensecurity.tileentity.TileEntityEntityDetector;
import pcl.opensecurity.tileentity.TileEntityKVM;
import pcl.opensecurity.tileentity.TileEntityKeypadLock;
import pcl.opensecurity.tileentity.TileEntityMagReader;
import pcl.opensecurity.tileentity.TileEntityRFIDReader;
import pcl.opensecurity.tileentity.TileEntitySecureDoor;
import pcl.opensecurity.tileentity.TileEntitySwitchableHub;
import pcl.opensecurity.util.OSBreakEvent;

/**
 * @author Caitlyn
 *
 */
public class ContentRegistry {

    public static Block magCardReaderBlock;
    public static Block rfidCardReaderBlock;
    public static Block cardWriterBlock;
    public static Block AlarmBlock;
    public static Block EntityDetectorBlock;
    public static Block SecurityDoorBlock;
    public static Block DoorControllerBlock;
    public static Block DataBlock;
    public static Block SwitchableHubBlock;
    public static Block KVMBlock;
    public static Block SecurityDoorPrivateBlock;
    public static Block DisplayPanelBlock;
    public static Block energyTurretBlock;
    public static Block keypadLockBlock;
    public static Block biometricScanner;
    public static Item magCardItem;
    public static Item rfidCardItem;
    public static Item securityDoorItem;
    public static Item securityDoorPrivateItem;
    public static Item rfidReaderCardItem;
    public static Item secureNetworkCardItem;
    public static Item damageUpgradeItem;
    public static Item cooldownUpgradeItem;
    public static Item energyUpgradeItem;
    public static Item movementUpgradeItem;
    public static ItemBlock securityitemBlock;
    public static ItemStack secureOS_disk;
    public static CreativeTabs CreativeTab;

    // Called on mod preinit()
    public static void preInit() {
        registerTabs();
        registerBlocks();
        registerEntities();
        registerItems();
        registerEvents();
    }

    public static void init() {
        registerRecipes();
        li.cil.oc.api.Driver.add(new RFIDReaderCardDriver());
        li.cil.oc.api.Driver.add((li.cil.oc.api.driver.Item) secureNetworkCardItem);
    }

    private static void registerEntities() {
        EntityRegistry.registerModEntity(EntityEnergyBolt.class, "energybolt", 0, OpenSecurity.instance, 64, 20, true);
    }

    private static void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new OSBreakEvent());
        OpenSecurity.logger.info("Registered Events");
    }

    private static void registerItems() {
        magCardItem = new ItemMagCard();
        GameRegistry.registerItem(magCardItem, "opensecurity.magCard");
        magCardItem.setCreativeTab(CreativeTab);

        rfidCardItem = new ItemRFIDCard();
        GameRegistry.registerItem(rfidCardItem, "opensecurity.rfidCard");
        rfidCardItem.setCreativeTab(CreativeTab);

        rfidReaderCardItem = new ItemRFIDReaderCard();
        GameRegistry.registerItem(rfidReaderCardItem, "opensecurity.rfidReaderCard");
        rfidReaderCardItem.setCreativeTab(CreativeTab);

        secureNetworkCardItem = new ItemSecureNetworkCard();
        GameRegistry.registerItem(secureNetworkCardItem, "opensecurity.secureNetworkCard");
        secureNetworkCardItem.setCreativeTab(CreativeTab);

        securityDoorItem = new ItemSecurityDoor(SecurityDoorBlock);
        GameRegistry.registerItem(securityDoorItem, "opensecurity.securityDoor");
        securityDoorItem.setCreativeTab(CreativeTab);

        securityDoorPrivateItem = new ItemSecurityDoorPrivate(SecurityDoorBlock);
        GameRegistry.registerItem(securityDoorPrivateItem, "opensecurity.securityDoorPrivate");
        securityDoorItem.setCreativeTab(CreativeTab);

        damageUpgradeItem = new ItemDamageUpgrade();
        GameRegistry.registerItem(damageUpgradeItem, "opensecurity.damageUpgrade");
        damageUpgradeItem.setCreativeTab(CreativeTab);

        cooldownUpgradeItem = new ItemCooldownUpgrade();
        GameRegistry.registerItem(cooldownUpgradeItem, "opensecurity.cooldownUpgrade");
        cooldownUpgradeItem.setCreativeTab(CreativeTab);

        energyUpgradeItem = new ItemEnergyUpgrade();
        GameRegistry.registerItem(energyUpgradeItem, "opensecurity.energyUpgrade");
        energyUpgradeItem.setCreativeTab(CreativeTab);

        movementUpgradeItem = new ItemMovementUpgrade();
        GameRegistry.registerItem(movementUpgradeItem, "opensecurity.movementUpgrade");
        movementUpgradeItem.setCreativeTab(CreativeTab);

        OpenSecurity.logger.info("Registered Items");
    }

    private static void registerBlocks() {
        magCardReaderBlock = new BlockMagReader();
        GameRegistry.registerBlock(magCardReaderBlock, "magreader");
        magCardReaderBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityMagReader.class, "MagCardTE");

        rfidCardReaderBlock = new BlockRFIDReader();
        GameRegistry.registerBlock(rfidCardReaderBlock, "rfidreader");
        rfidCardReaderBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityRFIDReader.class, "RFIDTE");

        cardWriterBlock = new BlockCardWriter();
        GameRegistry.registerBlock(cardWriterBlock, "rfidwriter");
        cardWriterBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityCardWriter.class, "RFIDWriterTE");

        AlarmBlock = new BlockAlarm();
        GameRegistry.registerBlock(AlarmBlock, "alarm");
        AlarmBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityAlarm.class, "AlarmTE");

        EntityDetectorBlock = new BlockEntityDetector();
        GameRegistry.registerBlock(EntityDetectorBlock, "entitydetector");
        EntityDetectorBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityEntityDetector.class, "EntityDetectorTE");

        DoorControllerBlock = new BlockDoorController();
        GameRegistry.registerBlock(DoorControllerBlock, "doorcontroller");
        DoorControllerBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityDoorController.class, "DoorControllerTE");

        SecurityDoorBlock = new BlockSecurityDoor();
        GameRegistry.registerBlock(SecurityDoorBlock, "SecurityDoor");

        SecurityDoorPrivateBlock = new BlockSecurityDoorPrivate();
        GameRegistry.registerBlock(SecurityDoorPrivateBlock, "SecurityDoorPrivate");

        GameRegistry.registerTileEntity(TileEntitySecureDoor.class, "SecureDoorTE");

        DataBlock = new BlockData();
        GameRegistry.registerBlock(DataBlock, OpenSecurity.MODID + ".DataBlock");
        DataBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityDataBlock.class, OpenSecurity.MODID + ".DataBlockTE");

        SwitchableHubBlock = new BlockSwitchableHub();
        GameRegistry.registerBlock(SwitchableHubBlock, OpenSecurity.MODID + ".SwitchableHub");
        SwitchableHubBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntitySwitchableHub.class, OpenSecurity.MODID + ".SwitchableHubTE");

        KVMBlock = new BlockKVM();
        GameRegistry.registerBlock(KVMBlock, OpenSecurity.MODID + ".BlockKVM");
        KVMBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityKVM.class, OpenSecurity.MODID + ".KVMTE");

        // if (BuildInfo.getVersionNumber() == 0) {
        // DisplayPanelBlock = new BlockDisplayPanel();
        // GameRegistry.registerBlock(DisplayPanelBlock, OpenSecurity.MODID + ".DisplayPanel");
        // DisplayPanelBlock.setCreativeTab(CreativeTab);
        // }

        GameRegistry.registerTileEntity(TileEntityDisplayPanel.class, OpenSecurity.MODID + ".DisplayPanelTE");

        energyTurretBlock = new BlockEnergyTurret();
        GameRegistry.registerBlock(energyTurretBlock, "energyTurretBlock");
        energyTurretBlock.setCreativeTab(CreativeTab);

        GameRegistry.registerTileEntity(TileEntityEnergyTurret.class, "EnergyTurret");

        keypadLockBlock = new BlockKeypadLock();
        GameRegistry.registerBlock(keypadLockBlock, "keypadLock");
        keypadLockBlock.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityKeypadLock.class, "KeypadLock");

        biometricScanner = new BlockBiometricReader();
        GameRegistry.registerBlock(biometricScanner, "biometricScanner");
        biometricScanner.setCreativeTab(CreativeTab);
        GameRegistry.registerTileEntity(TileEntityBiometricReader.class, "BiometricReader");

        OpenSecurity.logger.info("Registered Blocks");
    }

    private static void registerTabs() {
        CreativeTab = new CreativeTab("OpenSecurity");
    }

    private static void registerRecipes() {
        Callable<FileSystem> SOSFactory = new Callable<FileSystem>() {

            @Override
            public FileSystem call() {
                return li.cil.oc.api.FileSystem
                        .fromClass(OpenSecurity.class, OpenSecurity.MODID, "/lua/SecureOS/SecureOS/");
            }
        };
        secureOS_disk = li.cil.oc.api.Items.registerFloppy("SecureOS", 1, SOSFactory);

        // Vanilla Minecraft blocks/items
        String iron = "ingotIron";
        String diamond = "gemDiamond";
        String redstone = "dustRedstone";
        String obsidian = "obsidian";
        String glass = "blockGlassColorless";
        String stone = "stone";
        ItemStack stone_button = new ItemStack((Block) Block.blockRegistry.getObject("stone_button"));
        ItemStack paper = new ItemStack(Items.paper);
        ItemStack noteblock = new ItemStack(Blocks.noteblock);
        ItemStack door = new ItemStack(Items.iron_door);
        ItemStack gunpowder = new ItemStack(Items.gunpowder);
        ItemStack arrow = new ItemStack(Items.arrow);
        ItemStack piston = new ItemStack(Item.getItemFromBlock(Blocks.piston));
        ItemStack water = new ItemStack(Items.water_bucket);

        // Opencomputers blocks/items
        String t2microchip = "oc:circuitChip2";
        String t1microchip = "oc:circuitChip1";
        String t1ram = "oc:ram1";
        String pcb = "oc:materialCircuitBoardPrinted";
        String controlunit = "oc:materialCU";
        String wlancard = "oc:wlanCard";
        String cardbase = "oc:materialCard";
        String cable = "oc:cable";
        String transistor = "oc:materialTransistor";
        String numpad = "oc:materialNumPad";
        String batteryUpgrade = "oc:batteryUpgrade1";
        String oc_relay = "oc:relay";
        ItemStack floppy = li.cil.oc.api.Items.get("floppy").createItemStack(1);
        ItemStack datacard;
        if (li.cil.oc.api.Items.get("dataCard").createItemStack(1) != null) {
            datacard = li.cil.oc.api.Items.get("dataCard").createItemStack(1);
        } else {
            datacard = li.cil.oc.api.Items.get("dataCard1").createItemStack(1);
        }

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(rfidReaderCardItem, 1),
                        "MRM",
                        " N ",
                        "BC ",
                        'M',
                        t2microchip,
                        'R',
                        t1ram,
                        'N',
                        wlancard,
                        'B',
                        cardbase,
                        'C',
                        controlunit));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(EntityDetectorBlock, 1),
                        "MRM",
                        "   ",
                        "BC ",
                        'M',
                        t2microchip,
                        'R',
                        t1ram,
                        'B',
                        cardbase,
                        'C',
                        controlunit));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(rfidCardReaderBlock, 1),
                        " R ",
                        "PFT",
                        " C ",
                        'F',
                        rfidReaderCardItem,
                        'P',
                        pcb,
                        'R',
                        redstone,
                        'C',
                        cable,
                        'T',
                        t2microchip));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(DataBlock, 1),
                        " D ",
                        "PFT",
                        " C ",
                        'D',
                        datacard,
                        'P',
                        pcb,
                        'R',
                        redstone,
                        'C',
                        cable,
                        'T',
                        t2microchip));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(AlarmBlock, 1),
                        " R ",
                        "PNC",
                        " T ",
                        'N',
                        noteblock,
                        'P',
                        pcb,
                        'R',
                        redstone,
                        'C',
                        cable,
                        'T',
                        t2microchip));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(cardWriterBlock, 1),
                        "TRT",
                        "SUS",
                        "PC ",
                        'P',
                        pcb,
                        'C',
                        cable,
                        'T',
                        t2microchip,
                        'S',
                        transistor,
                        'U',
                        controlunit,
                        'R',
                        t1ram));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(magCardReaderBlock, 1),
                        "T T",
                        "S S",
                        "PC ",
                        'P',
                        pcb,
                        'C',
                        cable,
                        'T',
                        t2microchip,
                        'S',
                        transistor));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(rfidCardItem, 6),
                        "P P",
                        " S ",
                        "PTP",
                        'P',
                        paper,
                        'S',
                        transistor,
                        'T',
                        t1microchip));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(magCardItem, 6), "P P", " S ", "P P", 'P', paper, 'S', transistor));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(securityDoorItem, 1),
                        "TGT",
                        "ODO",
                        "SOS",
                        'G',
                        glass,
                        'D',
                        door,
                        'S',
                        transistor,
                        'T',
                        t2microchip,
                        'O',
                        obsidian));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(securityDoorPrivateItem, 1),
                        "TOT",
                        "ODO",
                        "SOS",
                        'D',
                        door,
                        'S',
                        transistor,
                        'T',
                        t2microchip,
                        'O',
                        obsidian));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(DoorControllerBlock, 1),
                        "TOT",
                        "OCO",
                        "SBS",
                        'B',
                        cable,
                        'C',
                        controlunit,
                        'S',
                        transistor,
                        'T',
                        t2microchip,
                        'O',
                        obsidian));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(SwitchableHubBlock, 1),
                        "TBT",
                        "BSB",
                        "RBR",
                        'B',
                        cable,
                        'S',
                        oc_relay,
                        'R',
                        transistor,
                        'T',
                        t2microchip,
                        'O',
                        obsidian));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(KVMBlock, 1),
                        " B ",
                        "BSB",
                        "RBR",
                        'B',
                        cable,
                        'S',
                        oc_relay,
                        'R',
                        transistor,
                        'T',
                        t2microchip,
                        'O',
                        obsidian));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(energyTurretBlock, 1),
                        "ABA",
                        "BCB",
                        "ABA",
                        'A',
                        iron,
                        'B',
                        t2microchip,
                        'C',
                        diamond));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(damageUpgradeItem, 1),
                        "A A",
                        " G ",
                        "A A",
                        'A',
                        arrow,
                        'G',
                        gunpowder));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(movementUpgradeItem, 1),
                        "R R",
                        " P ",
                        "R R",
                        'P',
                        piston,
                        'R',
                        redstone));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(cooldownUpgradeItem, 1),
                        "R R",
                        " W ",
                        "R R",
                        'W',
                        water,
                        'R',
                        redstone));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(energyUpgradeItem, 1),
                        "R R",
                        " B ",
                        "R R",
                        'B',
                        batteryUpgrade,
                        'R',
                        redstone));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(keypadLockBlock, 1),
                        "TIT",
                        "INI",
                        "ICI",
                        'T',
                        transistor,
                        'N',
                        numpad,
                        'C',
                        t1microchip,
                        'I',
                        iron));

        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(biometricScanner, 1),
                        "SIS",
                        "STS",
                        "SCS",
                        'T',
                        transistor,
                        'C',
                        t1microchip,
                        'I',
                        iron,
                        'S',
                        stone));

        GameRegistry.addShapelessRecipe(secureOS_disk, floppy, magCardItem);
        OpenSecurity.logger.info("Registered Recipes");
    }
}
