package wardentools.gui;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.items.armors.ArmorRegistry;
import wardentools.items.ItemRegistry;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModMain.MOD_ID);

    private static final Map<String, List<Supplier<Item>>> TAGGED_ITEMS = new HashMap<>();
    private static final List<Supplier<Item>> ALL_ITEMS = new ArrayList<>();

    static  {
        addItemToTag("abyssalite", ItemRegistry.ABYSSALITE);
        addItemToTag("abyssalite", ItemRegistry.CHISELED_ABYSSALITE);
        addItemToTag("abyssalite", ItemRegistry.ABYSSALITE_BRICKS);
        addItemToTag("abyssalite", ItemRegistry.ABYSSALITE_BRICKS_STAIRS);
        addItemToTag("abyssalite", ItemRegistry.ABYSSALITE_BRICKS_SLAB);
        addItemToTag("abyssalite", ItemRegistry.ABYSSALITE_BRICKS_WALL);
        addItemToTag("abyssalite", ItemRegistry.CRACKED_ABYSSALITE_BRICKS);
        addItemToTag("abyssalite", ItemRegistry.ABYSSALITE_COAL_ORE);
        addItemToTag("abyssalite", ItemRegistry.ABYSSALITE_LAPIS_ORE);
        addItemToTag("abyssalite", ItemRegistry.ABYSSALITE_DIAMOND_ORE);
        addItemToTag("abyssalite", ItemRegistry.ABYSSALITE_DEEP_ORE);

        addItemToTag("crystals", ItemRegistry.DEEP_FRAGMENT);
        addItemToTag("crystals", ItemRegistry.DEEPCRISTAL);
        addItemToTag("crystals", ItemRegistry.DEEPINGOTS);
        addItemToTag("crystals", ItemRegistry.DEEPBLOCK);
        addItemToTag("crystals", ItemRegistry.RADIANCE_FRAGMENT);
        addItemToTag("crystals", ItemRegistry.RADIANCE_CRISTAL);
        addItemToTag("crystals", ItemRegistry.RADIANCE_INGOTS);
        addItemToTag("crystals", ItemRegistry.CITRINE_FRAGMENT);
        addItemToTag("crystals", ItemRegistry.CITRINE);
        addItemToTag("crystals", ItemRegistry.CITRINE_BLOCK);
        addItemToTag("crystals", Items.ECHO_SHARD);
        addItemToTag("crystals", ItemRegistry.ECHO_CRISTAL);
        addItemToTag("crystals", ItemRegistry.ECHO_BLOCK);
        addItemToTag("crystals", ItemRegistry.RUBY_FRAGMENT);
        addItemToTag("crystals", ItemRegistry.RUBY);
        addItemToTag("crystals", ItemRegistry.RUBY_BLOCK);
        addItemToTag("crystals", ItemRegistry.MALACHITE_FRAGMENT);
        addItemToTag("crystals", ItemRegistry.MALACHITE);
        addItemToTag("crystals", ItemRegistry.MALACHITE_BLOCK);
        addItemToTag("crystals", ItemRegistry.PALE_SHARD);
        addItemToTag("crystals", ItemRegistry.PALE_CRISTAL);
        addItemToTag("crystals", ItemRegistry.PALE_CRISTAL_BLOCK);

        addItemToTag("deep_armor", ArmorRegistry.DEEPCRISTAL_HELMET);
        addItemToTag("deep_armor", ArmorRegistry.DEEPCRISTAL_CHESTPLATE);
        addItemToTag("deep_armor", ArmorRegistry.DEEPCRISTAL_LEGGINGS);
        addItemToTag("deep_armor", ArmorRegistry.DEEPCRISTAL_BOOTS);

        addItemToTag("radiant_armor", ArmorRegistry.RADIANCE_CRISTAL_HELMET);
        addItemToTag("radiant_armor", ArmorRegistry.RADIANCE_CRISTAL_CHESTPLATE);
        addItemToTag("radiant_armor", ArmorRegistry.RADIANCE_CRISTAL_LEGGINGS);
        addItemToTag("radiant_armor", ArmorRegistry.RADIANCE_CRISTAL_BOOTS);

        addItemToTag("darktree", ItemRegistry.DARKTREE_LOG);
        addItemToTag("darktree", ItemRegistry.DARKTREE_WOOD);
        addItemToTag("darktree", ItemRegistry.STRIPPED_DARKTREE_LOG);
        addItemToTag("darktree", ItemRegistry.STRIPPED_DARKTREE_WOOD);
        addItemToTag("darktree", ItemRegistry.DARKTREE_PLANKS);
        addItemToTag("darktree", ItemRegistry.DARKTREE_LEAVES);
        addItemToTag("darktree", ItemRegistry.DEEP_FRUIT);
        addItemToTag("darktree", ItemRegistry.DARKTREE_SAPLING);
        addItemToTag("darktree", ItemRegistry.DARKTREE_STAIRS);
        addItemToTag("darktree", ItemRegistry.DARKTREE_SLAB);
        addItemToTag("darktree", ItemRegistry.DARKTREE_FENCE);
        addItemToTag("darktree", ItemRegistry.DARKTREE_BUTTON);
        addItemToTag("darktree", ItemRegistry.DARKTREE_DOOR);
        addItemToTag("darktree", ItemRegistry.DARKTREE_TRAPDOOR);
        addItemToTag("darktree", ItemRegistry.DARKTREE_FENCE_GATE);
        addItemToTag("darktree", ItemRegistry.DARKTREE_PRESSURE_PLATE);
        addItemToTag("darktree", ItemRegistry.DARK_STICK);
        addItemToTag("darktree", ItemRegistry.DARKTREE_BOAT);
        addItemToTag("darktree", ItemRegistry.DARKTREE_CHEST_BOAT);

        addItemToTag("dark_vegetal", ItemRegistry.BLUE_BUSH);
        addItemToTag("dark_vegetal", ItemRegistry.DEEPFLOWER);
        addItemToTag("dark_vegetal", ItemRegistry.TALL_DARK_GRASS);
        addItemToTag("dark_vegetal", ItemRegistry.DARK_GRASS);
        addItemToTag("dark_vegetal", ItemRegistry.BLUE_GLOW_BERRIES);
        addItemToTag("dark_vegetal", ItemRegistry.NOCTILURE_TREAT);

        addItemToTag("whitetree", ItemRegistry.WHITETREE_LOG);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_WOOD);
        addItemToTag("whitetree", ItemRegistry.STRIPPED_WHITETREE_LOG);
        addItemToTag("whitetree", ItemRegistry.STRIPPED_WHITETREE_WOOD);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_LEAVES);
        addItemToTag("whitetree", ItemRegistry.WHITE_SEED);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_SAPLING);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_PLANKS);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_STAIRS);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_SLAB);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_FENCE);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_BUTTON);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_DOOR);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_TRAPDOOR);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_FENCE_GATE);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_PRESSURE_PLATE);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_BOAT);
        addItemToTag("whitetree", ItemRegistry.WHITETREE_CHEST_BOAT);

        addItemToTag("white_vegetal", ItemRegistry.WHITE_GRASS);
        addItemToTag("white_vegetal", ItemRegistry.TALL_WHITE_GRASS);
        addItemToTag("white_vegetal", ItemRegistry.WHITE_TORCHFLOWER);

        addItemToTag("dirt", ItemRegistry.DARKDIRT);
        addItemToTag("dirt", ItemRegistry.DARKGRASS_BLOCK);

        addItemToTag("egg", ItemRegistry.DEEPLURKER_EGG);
        addItemToTag("egg", ItemRegistry.PALEWANDERER_EGG);
        addItemToTag("egg", ItemRegistry.TEMPER_EGG);
        addItemToTag("egg", ItemRegistry.PARASYTE_EGG);
        addItemToTag("egg", ItemRegistry.PROTECTOR_EGG);
        addItemToTag("egg", ItemRegistry.CONTAGION_INCARNATION_EGG);
        addItemToTag("egg", ItemRegistry.NOCTILURE_EGG);
        addItemToTag("egg", ItemRegistry.SHADOW_EGG);

        addItemToTag("corrupted", ItemRegistry.WARDEN_HEART);
        addItemToTag("corrupted", ItemRegistry.CORRUPTED_ESSENCE);
        addItemToTag("corrupted", ItemRegistry.CORRUPTED_VESSEL);
        addItemToTag("corrupted", ItemRegistry.WIND_WHISPERER);
        addItemToTag("corrupted", ItemRegistry.SOLID_CORRUPTION);
        addItemToTag("corrupted", ItemRegistry.DYSFUNCTIONNING_CATALYST);

        addItemToTag("radiant", ItemRegistry.RADIANCE_CATALYST);
        addItemToTag("radiant", ItemRegistry.PURE_ESSENCE);
        addItemToTag("radiant", ItemRegistry.PURE_VESSEL);
        addItemToTag("radiant", ItemRegistry.PROTECTOR_HEART);
        addItemToTag("radiant", ItemRegistry.DYING_PROTECTOR_HEART);
        addItemToTag("radiant", ItemRegistry.PROTECTOR_INVOKER);

        addItemToTag("staff", ItemRegistry.ABYSS_DIVER);
        addItemToTag("staff", ItemRegistry.RADIANT_STAFF);

        addItemToTag("sculk", Items.SCULK);
        addItemToTag("sculk", Items.SCULK_CATALYST);
        addItemToTag("sculk", Items.SCULK_SENSOR);
        addItemToTag("sculk", Items.SCULK_SHRIEKER);
        addItemToTag("sculk", Items.SCULK_VEIN);

        addItemToTag("building", ItemRegistry.BLACK_LANTERN);

        addAllItemsFromRegistry();
    }

    // Creating the tabs here
    public static final RegistryObject<CreativeModeTab> ALL
            = CREATIVE_MODE_TABS.register("all",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.DARKGRASS_BLOCK.get()))
                    .title(Component.translatable("creativetab.all"))
                    .displayItems((pParameters, event) -> {
                        ALL_ITEMS.forEach(item -> event.accept(item.get()));
                    }).build());

    public static final RegistryObject<CreativeModeTab> BLOCKS
            = CREATIVE_MODE_TABS.register("blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.ABYSSALITE.get()))
                    .title(Component.translatable("creativetab.blocks"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag("dirt").forEach(item -> event.accept(item.get()));
                        getItemsByTag("abyssalite").forEach(item -> event.accept(item.get()));
                        getItemsByTag("sculk").forEach(item -> event.accept(item.get()));
                        getItemsByTag("building").forEach(item -> event.accept(item.get()));
                    }).build());

    public static final RegistryObject<CreativeModeTab> VEGETATION
            = CREATIVE_MODE_TABS.register("vegetation",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.BLUE_GLOW_BERRIES.get()))
                    .title(Component.translatable("creativetab.vegetation"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag("dark_vegetal").forEach(item -> event.accept(item.get()));
                        getItemsByTag("white_vegetal").forEach(item -> event.accept(item.get()));
                    }).build());

    public static final RegistryObject<CreativeModeTab> DARKTREE
            = CREATIVE_MODE_TABS.register("darktree",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.DARKTREE_LOG.get()))
                    .title(Component.translatable("creativetab.darktree"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag("darktree").forEach(item -> event.accept(item.get()));
                    }).build());

    public static final RegistryObject<CreativeModeTab> WHITETREE
            = CREATIVE_MODE_TABS.register("whitetree",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.WHITETREE_LOG.get()))
                    .title(Component.translatable("creativetab.whitetree"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag("whitetree").forEach(item -> event.accept(item.get()));
                    }).build());

    public static final RegistryObject<CreativeModeTab> TOOLS
            = CREATIVE_MODE_TABS.register("tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get()))
                    .title(Component.translatable("creativetab.tools"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag("deep_armor").forEach(item -> event.accept(item.get()));
                        getItemsByTag("radiant_armor").forEach(item -> event.accept(item.get()));
                        getItemsByTag("staff").forEach(item -> event.accept(item.get()));
                    }).build());

    public static final RegistryObject<CreativeModeTab> CRYSTALS
            = CREATIVE_MODE_TABS.register("crystals",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.CITRINE_FRAGMENT.get()))
                    .title(Component.translatable("creativetab.crystals"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag("crystals").forEach(item -> event.accept(item.get()));
                    }).build());

    public static final RegistryObject<CreativeModeTab> MISC
            = CREATIVE_MODE_TABS.register("misc",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.CORRUPTED_ESSENCE.get()))
                    .title(Component.translatable("creativetab.misc"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag("corrupted").forEach(item -> event.accept(item.get()));
                        getItemsByTag("radiant").forEach(item -> event.accept(item.get()));
                    }).build());

    public static final RegistryObject<CreativeModeTab> EGG
            = CREATIVE_MODE_TABS.register("egg",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.DEEPLURKER_EGG.get()))
                    .title(Component.translatable("creativetab.egg"))
                    .displayItems((pParameters, event) -> {
                        getItemsByTag("egg").forEach(item -> event.accept(item.get()));
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static void addItemToTag(String tag, Supplier<Item> item) {
        TAGGED_ITEMS.computeIfAbsent(tag, k -> new ArrayList<>()).add(item);
    }

    public static void addItemToTag(String tag, Item item) {
        addItemToTag(tag, () -> item);
    }

    public static List<Supplier<Item>> getItemsByTag(String tag) {
        return TAGGED_ITEMS.getOrDefault(tag, Collections.emptyList());
    }

    public static void addAllItemsFromRegistry() {
        Field[] fields = ItemRegistry.class.getDeclaredFields();
        for (Field field : fields) {
            if (RegistryObject.class.isAssignableFrom(field.getType())) {
                try {
                    @SuppressWarnings("unchecked")
                    RegistryObject<Item> registryObject = (RegistryObject<Item>) field.get(null);
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

}