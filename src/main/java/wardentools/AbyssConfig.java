package wardentools;

import net.neoforged.neoforge.common.ModConfigSpec;


public class AbyssConfig {

    public static class CLIENT {

        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static final ModConfigSpec.ConfigValue<Integer> ABYSS_FOG_STORM_INTENSITY = BUILDER
                .translation("config.wardentools.title.abyss_fog_intensity")
                .comment("Set the distance of the fog when a storm is active in the Abyss (in blocks)")
                .defineInRange("abyssFogIntensity", 20, 10, 512);

        public static final ModConfigSpec.ConfigValue<Boolean> DISPLAY_WIND_MESSAGES = BUILDER
                .translation("config.wardentools.title.display_wind_messages")
                .comment("Wind messages are still added to the journal")
                .define("displayWindMessages", true);

        static ModConfigSpec SPEC = BUILDER.build();

    }

    public static class SERVER {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static final ModConfigSpec.ConfigValue<Integer> ABYSS_ARMOR_PROTECTION = BUILDER
                .translation("config.wardentools.title.abyss_armor_protection")
                .comment("Set the defense value for abyss chestplate. Other pieces will be scaled based on that value. (netherite is 8)")
                .worldRestart()
                .defineInRange("abyssArmorProtection", 9, 1, 20);

        public static final ModConfigSpec.ConfigValue<Boolean> DO_ABYSS_WEATHER = BUILDER
                .translation("config.wardentools.title.do_abyss_weather")
                .comment("Enable or disable weather changes in the Abyss dimension")
                .define("doAbyssWeather", true);

        public static final ModConfigSpec.ConfigValue<Integer> WARDEN_HEART_LASER = BUILDER
                .translation("config.wardentools.title.warden_heart_laser")
                .comment("Set damage dealt by the Warden Heart laser attack")
                .defineInRange("wardenHeartLaser", 10, 1, 1000);


        static ModConfigSpec SPEC = BUILDER.build();

    }

    public static class COMMON {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        static ModConfigSpec SPEC = BUILDER.build();
    }

}
