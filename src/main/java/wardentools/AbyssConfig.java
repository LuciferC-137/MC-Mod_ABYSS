package wardentools;

import net.minecraftforge.common.ForgeConfigSpec;


public class AbyssConfig {

    public static class CLIENT {

        private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        public static final ForgeConfigSpec.ConfigValue<Integer> ABYSS_FOG_STORM_INTENSITY = BUILDER
                .translation("config.wardentools.title.abyss_fog_intensity")
                .comment("Set the distance of the fog when a storm is active in the Abyss (in blocks)")
                .defineInRange("abyssFogIntensity", 20, 10, 512);

        public static final ForgeConfigSpec.ConfigValue<Boolean> DISPLAY_WIND_MESSAGES = BUILDER
                .translation("config.wardentools.title.display_wind_messages")
                .comment("Wind messages are still added to the journal")
                .define("displayWindMessages", true);

        public static final ForgeConfigSpec SPEC = BUILDER.build();

    }

    public static class SERVER {
        private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        public static final ForgeConfigSpec.ConfigValue<Boolean> DO_ABYSS_WEATHER = BUILDER
                .translation("config.wardentools.title.do_abyss_weather")
                .comment("Enable or disable weather changes in the Abyss dimension")
                .define("doAbyssWeather", true);

        public static final ForgeConfigSpec SPEC = BUILDER.build();

    }

    public static class COMMON {
        private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        public static final ForgeConfigSpec.ConfigValue<Integer> ABYSS_ARMOR_PROTECTION = BUILDER
                .translation("config.wardentools.title.abyss_armor_protection")
                .comment("Set the defense value for abyss chestplate. Other pieces will be scaled based on that value. (netherite is 8)")
                .worldRestart()
                .defineInRange("abyssArmorProtection", 9, 1, 20);

        public static final ForgeConfigSpec.ConfigValue<Integer> WARDEN_HEART_LASER = BUILDER
                .translation("config.wardentools.title.warden_heart_laser")
                .comment("Set damage dealt by the Warden Heart laser attack")
                .defineInRange("wardenHeartLaser", 10, 1, 1000);

        public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_VOID_FALL_TELEPORT = BUILDER
                .translation("config.wardentools.title.enable_void_fall_teleport")
                .comment("Enable teleporting to the Abyss when falling into the void")
                .define("enableVoidFallTeleport", true);

        public static final ForgeConfigSpec SPEC = BUILDER.build();
    }

}
