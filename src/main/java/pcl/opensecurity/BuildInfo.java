package pcl.opensecurity;

import net.minecraft.launchwrapper.Launch;

/**
 * This file is automatically updated by Jenkins as part of the CI build script in Ant. Don't put any pre-set values
 * here.
 *
 * @author AfterLifeLochie, stolen from LanteaCraft, another fine PC-Logix Minecraft mod.
 */
public class BuildInfo {

    public static final String modName = "OpenSecurity";
    public static final String modID = "opensecurity";

    public static final String versionNumber = Tags.VERSION;
    @Deprecated
    public static final String buildNumber = "0";

    @Deprecated
    public static int getBuildNumber() {
        return 0;
    }

    @Deprecated
    public static int getVersionNumber() {
        return 1;
    }

    @Deprecated
    public static boolean isDevelopmentEnvironment() {
        return (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}
