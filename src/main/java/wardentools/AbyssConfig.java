package wardentools;

import net.neoforged.neoforge.common.ModConfigSpec;


public class AbyssConfig {

    public static class CLIENT {

        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static final ModConfigSpec.ConfigValue<Integer> ABYSS_FOG_STORM_INTENSITY = BUILDER
                .translation("config.wardentools.abyss_fog_intensity")
                .comment("The distance in blocks of the fog during storms in the Abyss dimension (0-100)")
                .defineInRange("abyssFogIntensity", 20, 10, 512);

        public static final ModConfigSpec.ConfigValue<Boolean> DISPLAY_WIND_MESSAGES = BUILDER
                .translation("config.wardentools.display_wind_messages")
                .comment("Enable or disable wind message display in the Abyss dimension")
                .define("displayWindMessages", true);

        static ModConfigSpec SPEC = BUILDER.build();

    }

    public static class SERVER {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static final ModConfigSpec.ConfigValue<Integer> ABYSS_ARMOR_PROTECTION = BUILDER
                .translation("config.wardentools.abyss_armor_protection")
                .comment("The base protection value for chestplate Abyss armor pieces (netherite is 8)")
                .defineInRange("abyssArmorProtection", 9, 1, 20);

        public static final ModConfigSpec.ConfigValue<Integer> SCYTHE_DAMAGE = BUILDER
                .translation("config.wardentools.scythe_damage")
                .comment("The base damage value for the Abyss Scythe weapon")
                .defineInRange("scytheDamage", 12, 1, 30);

        public static final ModConfigSpec.ConfigValue<Integer> SPEAR_DAMAGE = BUILDER
                .translation("config.wardentools.spear_damage")
                .comment("The base damage value for the Abyss Spear weapon")
                .defineInRange("spearDamage", 10, 1, 30);

        public static final ModConfigSpec.ConfigValue<Boolean> DO_ABYSS_WEATHER = BUILDER
                .translation("config.wardentools.do_abyss_weather")
                .comment("Enable or disable weather in the Abyss dimension")
                .define("doAbyssWeather", true);


        static ModConfigSpec SPEC = BUILDER.build();

    }

    public static class COMMON {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        static ModConfigSpec SPEC = BUILDER.build();
    }

}
