package wardentools.gui;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.items.armors.ArmorRegistry;
import wardentools.items.ItemRegistry;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModMain.MOD_ID);

    private static final Map<Tag, List<Supplier<Item>>> TAGGED_ITEMS = new HashMap<>();
    private static final List<Supplier<Item>> ALL_ITEMS = new ArrayList<>();

    static  {
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.CHISELED_ABYSSALITE);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE_BRICKS);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE_BRICKS_STAIRS);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE_BRICKS_SLAB);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE_BRICKS_WALL);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.CRACKED_ABYSSALITE_BRICKS);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.CRACKED_ABYSSALITE_BRICKS_STAIR);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.CRACKED_ABYSSALITE_BRICKS_SLAB);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.CRACKED_ABYSSALITE_BRICKS_WALL);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE_COAL_ORE);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE_LAPIS_ORE);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE_REDSTONE_ORE);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE_DIAMOND_ORE);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.ABYSSALITE_DEEP_ORE);
        addItemToTag(Tag.ABYSSALITE, ItemRegistry.CORRUPTED_ABYSSALITE);

        addItemToTag(Tag.CRYSTALS, Items.AMETHYST_SHARD);
        addItemToTag(Tag.CRYSTALS, Items.AMETHYST_CLUSTER);
        addItemToTag(Tag.CRYSTALS, Items.AMETHYST_BLOCK);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.RUBY_FRAGMENT);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.RUBY);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.RUBY_BLOCK);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.CITRINE_FRAGMENT);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.CITRINE);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.CITRINE_BLOCK);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.MALACHITE_FRAGMENT);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.MALACHITE);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.MALACHITE_BLOCK);
        addItemToTag(Tag.CRYSTALS, Items.ECHO_SHARD);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.ECHO_CRISTAL);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.ECHO_BLOCK);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.PALE_SHARD);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.PALE_CRISTAL);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.PALE_CRISTAL_BLOCK);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.DEEP_FRAGMENT);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.DEEPCRISTAL);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.DEEPINGOTS);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.DEEPBLOCK);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.RADIANCE_FRAGMENT);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.RADIANCE_CRISTAL);
        addItemToTag(Tag.CRYSTALS, ItemRegistry.RADIANCE_INGOTS);

        addItemToTag(Tag.DEEP_ARMOR, ArmorRegistry.DEEPCRISTAL_HELMET);
        addItemToTag(Tag.DEEP_ARMOR, ArmorRegistry.DEEPCRISTAL_CHESTPLATE);
        addItemToTag(Tag.DEEP_ARMOR, ArmorRegistry.DEEPCRISTAL_LEGGINGS);
        addItemToTag(Tag.DEEP_ARMOR, ArmorRegistry.DEEPCRISTAL_BOOTS);

        addItemToTag(Tag.RADIANT_ARMOR, ArmorRegistry.RADIANCE_CRISTAL_HELMET);
        addItemToTag(Tag.RADIANT_ARMOR, ArmorRegistry.RADIANCE_CRISTAL_CHESTPLATE);
        addItemToTag(Tag.RADIANT_ARMOR, ArmorRegistry.RADIANCE_CRISTAL_LEGGINGS);
        addItemToTag(Tag.RADIANT_ARMOR, ArmorRegistry.RADIANCE_CRISTAL_BOOTS);

        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_LOG);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_WOOD);
        addItemToTag(Tag.DARKTREE, ItemRegistry.STRIPPED_DARKTREE_LOG);
        addItemToTag(Tag.DARKTREE, ItemRegistry.STRIPPED_DARKTREE_WOOD);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_PLANKS);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_LEAVES);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DEEP_FRUIT);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_SAPLING);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_STAIRS);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_SLAB);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_FENCE);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_BUTTON);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_DOOR);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_TRAPDOOR);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_FENCE_GATE);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_PRESSURE_PLATE);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARK_STICK);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_BOAT);
        addItemToTag(Tag.DARKTREE, ItemRegistry.DARKTREE_CHEST_BOAT);

        addItemToTag(Tag.DARK_VEGETAL, ItemRegistry.DEPTH_BERRIES);
        addItemToTag(Tag.DARK_VEGETAL, ItemRegistry.BLUE_BUSH);
        addItemToTag(Tag.DARK_VEGETAL, ItemRegistry.DEEPFLOWER);
        addItemToTag(Tag.DARK_VEGETAL, ItemRegistry.TALL_DARK_GRASS);
        addItemToTag(Tag.DARK_VEGETAL, ItemRegistry.DARK_GRASS);
        addItemToTag(Tag.DARK_VEGETAL, ItemRegistry.BLUE_GLOW_BERRIES);

        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_LOG);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_WOOD);
        addItemToTag(Tag.WHITETREE, ItemRegistry.STRIPPED_WHITETREE_LOG);
        addItemToTag(Tag.WHITETREE, ItemRegistry.STRIPPED_WHITETREE_WOOD);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_LEAVES);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITE_SEED);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_SAPLING);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_PLANKS);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_STAIRS);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_SLAB);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_FENCE);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_BUTTON);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_DOOR);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_TRAPDOOR);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_FENCE_GATE);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_PRESSURE_PLATE);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_BOAT);
        addItemToTag(Tag.WHITETREE, ItemRegistry.WHITETREE_CHEST_BOAT);

        addItemToTag(Tag.WHITE_VEGETAL, ItemRegistry.WHITE_GRASS);
        addItemToTag(Tag.WHITE_VEGETAL, ItemRegistry.TALL_WHITE_GRASS);
        addItemToTag(Tag.WHITE_VEGETAL, ItemRegistry.WHITE_TORCHFLOWER);

        addItemToTag(Tag.NATURE, ItemRegistry.NOCTILURE_FEATHER);
        addItemToTag(Tag.NATURE, ItemRegistry.WANDERER_PAW);
        addItemToTag(Tag.NATURE, ItemRegistry.NOCTILURE_TREAT);
        addItemToTag(Tag.NATURE, ItemRegistry.LURKER_EYE);

        addItemToTag(Tag.DIRT, ItemRegistry.DARKDIRT);
        addItemToTag(Tag.DIRT, ItemRegistry.DARKGRASS_BLOCK);

        addItemToTag(Tag.EGG, ItemRegistry.DEEPLURKER_EGG);
        addItemToTag(Tag.EGG, ItemRegistry.PALEWANDERER_EGG);
        addItemToTag(Tag.EGG, ItemRegistry.TEMPER_EGG);
        addItemToTag(Tag.EGG, ItemRegistry.PARASYTE_EGG);
        addItemToTag(Tag.EGG, ItemRegistry.PROTECTOR_EGG);
        addItemToTag(Tag.EGG, ItemRegistry.CONTAGION_INCARNATION_EGG);
        addItemToTag(Tag.EGG, ItemRegistry.NOCTILURE_EGG);
        addItemToTag(Tag.EGG, ItemRegistry.SHADOW_EGG);
        addItemToTag(Tag.EGG, ItemRegistry.CRYSTAL_GOLEM_SPAWN_EGG);

        addItemToTag(Tag.CORRUPTED, ItemRegistry.WARDEN_HEART);
        addItemToTag(Tag.CORRUPTED, ItemRegistry.CORRUPTED_ESSENCE);
        addItemToTag(Tag.CORRUPTED, ItemRegistry.CORRUPTED_VESSEL);
        addItemToTag(Tag.CORRUPTED, ItemRegistry.WIND_WHISPERER);
        addItemToTag(Tag.CORRUPTED, ItemRegistry.SOLID_CORRUPTION);
        addItemToTag(Tag.CORRUPTED, ItemRegistry.DYSFUNCTIONNING_CATALYST);

        addItemToTag(Tag.RADIANT, ItemRegistry.RADIANCE_CATALYST);
        addItemToTag(Tag.RADIANT, ItemRegistry.PURE_ESSENCE);
        addItemToTag(Tag.RADIANT, ItemRegistry.PURE_VESSEL);
        addItemToTag(Tag.RADIANT, ItemRegistry.PROTECTOR_HEART);
        addItemToTag(Tag.RADIANT, ItemRegistry.DYING_PROTECTOR_HEART);
        addItemToTag(Tag.RADIANT, ItemRegistry.PROTECTOR_INVOKER);

        addItemToTag(Tag.STAFF, ItemRegistry.ABYSS_DIVER);
        addItemToTag(Tag.STAFF, ItemRegistry.RADIANT_STAFF);

        addItemToTag(Tag.WEAPONS, ItemRegistry.ABYSSAL_SCYTHE);
        addItemToTag(Tag.WEAPONS, ItemRegistry.RADIANT_SPEAR);

        addItemToTag(Tag.SCULK, Items.SCULK);
        addItemToTag(Tag.SCULK, Items.SCULK_CATALYST);
        addItemToTag(Tag.SCULK, Items.SCULK_SENSOR);
        addItemToTag(Tag.SCULK, Items.SCULK_SHRIEKER);
        addItemToTag(Tag.SCULK, Items.SCULK_VEIN);
        addItemToTag(Tag.SCULK, ItemRegistry.SCULK_TENDRIL_BLOCK);
        addItemToTag(Tag.SCULK, ItemRegistry.LIVING_SPROUT);

        addItemToTag(Tag.BUILDING, ItemRegistry.BLACK_LANTERN);
        addItemToTag(Tag.BUILDING, ItemRegistry.REINFORCED_GLASS);

        addItemToTag(Tag.JEWELRY, ItemRegistry.MIND_TIARA);
        addItemToTag(Tag.JEWELRY, ItemRegistry.RING_OF_WILL);
        addItemToTag(Tag.JEWELRY, ItemRegistry.STRENGTH_BRACELET);
        addItemToTag(Tag.JEWELRY, ItemRegistry.PENDANT_OF_BALANCE);
        addItemToTag(Tag.JEWELRY, ItemRegistry.SHADOW_ORNAMENT);
        addItemToTag(Tag.JEWELRY, ItemRegistry.LIGHT_ORNAMENT);

        addItemToTag(Tag.MISC, ItemRegistry.CONTAGION_INCARNATION_SKULL);
        addItemToTag(Tag.MISC, ItemRegistry.SOUL_SPAWNER);
        addItemToTag(Tag.MISC, ItemRegistry.WHISTLE);
        addItemToTag(Tag.MISC, ItemRegistry.WIND_JOURNAL);
        addItemToTag(Tag.MISC, ItemRegistry.GRAMOPHONE);
        addItemToTag(Tag.MISC, ItemRegistry.SONIC_BLASTER);
        addItemToTag(Tag.MISC, ItemRegistry.CRYSTAL_RESONATOR);
        addItemToTag(Tag.MISC, ItemRegistry.CRYSTAL_INFUSER);
        addItemToTag(Tag.MISC, ItemRegistry.GOLEM_STONE);
        addItemToTag(Tag.MISC, ItemRegistry.ANCIENT_CITADEL_MAP);

        addItemToTag(Tag.DISC, ItemRegistry.ABYSS_MUSIC_DISC);
        addItemToTag(Tag.DISC, ItemRegistry.INCARNATION_MUSIC_DISC);
        addItemToTag(Tag.DISC, ItemRegistry.DEEP_FOREST_MUSIC_DISC);
        addItemToTag(Tag.DISC, ItemRegistry.WHITE_FOREST_MUSIC_DISC);
        addItemToTag(Tag.DISC, ItemRegistry.REFLECTION_MUSIC_DISC);

        addAllItemsFromRegistry();
    }

    // Creating the tabs here
    public static final Supplier<CreativeModeTab> ALL
            = CREATIVE_MODE_TABS.register("all",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.DARKGRASS_BLOCK.get()))
                    .title(Component.translatable("creativetab.all"))
                    .displayItems((pParameters, event) -> {
                        ALL_ITEMS.forEach(item -> event.accept(item.get()));
                    }).build());

    public static final Supplier<CreativeModeTab> BLOCKS
            = CREATIVE_MODE_TABS.register("blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.ABYSSALITE.get()))
                    .title(Component.translatable("creativetab.blocks"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag(Tag.DIRT).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.ABYSSALITE).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.SCULK).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.BUILDING).forEach(item -> event.accept(item.get()));
                    }).build());

    public static final Supplier<CreativeModeTab> NATURE
            = CREATIVE_MODE_TABS.register("nature",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.BLUE_GLOW_BERRIES.get()))
                    .title(Component.translatable("creativetab.nature"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag(Tag.DARK_VEGETAL).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.WHITE_VEGETAL).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.NATURE).forEach(item -> event.accept(item.get()));
                    }).build());

    public static final Supplier<CreativeModeTab> DARKTREE
            = CREATIVE_MODE_TABS.register("darktree",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.DARKTREE_LOG.get()))
                    .title(Component.translatable("creativetab.darktree"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag(Tag.DARKTREE).forEach(item -> event.accept(item.get()));
                    }).build());

    public static final Supplier<CreativeModeTab> WHITETREE
            = CREATIVE_MODE_TABS.register("whitetree",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.WHITETREE_LOG.get()))
                    .title(Component.translatable("creativetab.whitetree"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag(Tag.WHITETREE).forEach(item -> event.accept(item.get()));
                    }).build());

    public static final Supplier<CreativeModeTab> TOOLS
            = CREATIVE_MODE_TABS.register("tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get()))
                    .title(Component.translatable("creativetab.tools"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag(Tag.DEEP_ARMOR).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.RADIANT_ARMOR).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.STAFF).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.WEAPONS).forEach(item -> event.accept(item.get()));
                    }).build());

    public static final RegistryObject<CreativeModeTab> CRYSTALS
            = CREATIVE_MODE_TABS.register("crystals",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.CITRINE_FRAGMENT.get()))
                    .title(Component.translatable("creativetab.crystals"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag(Tag.CRYSTALS).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.JEWELRY).forEach(item -> event.accept(item.get()));
                    }).build());

    public static final Supplier<CreativeModeTab> MISC
            = CREATIVE_MODE_TABS.register("misc",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.CORRUPTED_ESSENCE.get()))
                    .title(Component.translatable("creativetab.misc"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag(Tag.CORRUPTED).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.RADIANT).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.DISC).forEach(item -> event.accept(item.get()));
                        getItemsByTag(Tag.MISC).forEach(item -> event.accept(item.get()));
                    }).build());

    public static final Supplier<CreativeModeTab> EGG
            = CREATIVE_MODE_TABS.register("egg",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.DEEPLURKER_EGG.get()))
                    .title(Component.translatable("creativetab.egg"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag(Tag.EGG).forEach(item -> event.accept(item.get()));
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static void addItemToTag(Tag tag, Supplier<Item> item) {
        TAGGED_ITEMS.computeIfAbsent(tag, k -> new ArrayList<>()).add(item);
    }

    public static void addItemToTag(Tag tag, Item item) {
        addItemToTag(tag, () -> item);
    }

    public static List<Supplier<Item>> getItemsByTag(Tag tag) {
        return TAGGED_ITEMS.getOrDefault(tag, Collections.emptyList());
    }

    public static void addAllItemsFromRegistry() {
        Field[] fields = ItemRegistry.class.getDeclaredFields();
        for (Field field : fields) {
            if (Supplier.class.isAssignableFrom(field.getType())) {
                try {
                    @SuppressWarnings("unchecked")
                    Supplier<Item> registryObject = (Supplier<Item>) field.get(null);
                    addItemToAll(registryObject);
                } catch (IllegalAccessException e) {
                    System.out.println("Error while adding all items to creative tabs");
                }
            }
        }
    }

    public static void addItemToAll(Supplier<Item> item) {
        ALL_ITEMS.add(item);
    }

    public static enum Tag {
        ABYSSALITE("abyssalite"),
        CRYSTALS("crystals"),
        DEEP_ARMOR("deep_armor"),
        RADIANT_ARMOR("radiant_armor"),
        DARKTREE("darktree"),
        DARK_VEGETAL("dark_vegetal"),
        WHITETREE("whitetree"),
        WHITE_VEGETAL("white_vegetal"),
        NATURE("nature"),
        DIRT("dirt"),
        EGG("egg"),
        CORRUPTED("corrupted"),
        RADIANT("radiant"),
        STAFF("staff"),
        WEAPONS("weapons"),
        SCULK("sculk"),
        BUILDING("building"),
        JEWELRY("jewelry"),
        MISC("misc"),
        DISC("disc");

        private final String key;

        Tag(String key) {this.key = key;}

        public String getKey() {return key;}
    }

}