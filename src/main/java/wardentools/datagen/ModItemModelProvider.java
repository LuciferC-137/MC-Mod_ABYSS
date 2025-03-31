package wardentools.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import wardentools.ModMain;
import wardentools.items.armors.ArmorRegistry;
import wardentools.block.BlockRegistry;
import wardentools.items.ItemRegistry;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials
            = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ModMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    	//Armors
        trimmedArmorItem(ArmorRegistry.DEEPCRISTAL_HELMET);
        trimmedArmorItem(ArmorRegistry.DEEPCRISTAL_CHESTPLATE);
        trimmedArmorItem(ArmorRegistry.DEEPCRISTAL_LEGGINGS);
        trimmedArmorItem(ArmorRegistry.DEEPCRISTAL_BOOTS);
        trimmedArmorItem(ArmorRegistry.RADIANCE_CRISTAL_HELMET);
        trimmedArmorItem(ArmorRegistry.RADIANCE_CRISTAL_CHESTPLATE);
        trimmedArmorItem(ArmorRegistry.RADIANCE_CRISTAL_LEGGINGS);
        trimmedArmorItem(ArmorRegistry.RADIANCE_CRISTAL_BOOTS);
        
        //Simple Item
        simpleItem(ItemRegistry.DEEPINGOTS);
        simpleItem(ItemRegistry.WARDEN_HEART);
        simpleItem(ItemRegistry.DEEP_FRUIT);
        simpleItem(ItemRegistry.WIND_WHISPERER);
        simpleItem(ItemRegistry.RADIANCE_FRAGMENT);
        simpleItem(ItemRegistry.CORRUPTED_ESSENCE);
        simpleItem(ItemRegistry.CORRUPTED_VESSEL);
        simpleItem(ItemRegistry.DARK_STICK);
        simpleItem(ItemRegistry.PURE_ESSENCE);
        simpleItem(ItemRegistry.WHITE_SEED);
        simpleItem(ItemRegistry.RADIANT_CORE);
        simpleItem(ItemRegistry.PURE_VESSEL);
        simpleItem(ItemRegistry.PROTECTOR_HEART);
        simpleItem(ItemRegistry.DYING_PROTECTOR_HEART);
        simpleItem(ItemRegistry.DARKTREE_BOAT);
        simpleItem(ItemRegistry.DARKTREE_CHEST_BOAT);
        simpleItem(ItemRegistry.WHITETREE_BOAT);
        simpleItem(ItemRegistry.WHITETREE_CHEST_BOAT);
        simpleItem(ItemRegistry.DEEP_FRAGMENT);
        simpleItem(ItemRegistry.CITRINE_FRAGMENT);
        simpleItem(ItemRegistry.RUBY_FRAGMENT);
        simpleItem(ItemRegistry.MALACHITE_FRAGMENT);
        simpleItem(ItemRegistry.PALE_SHARD);
        simpleItem(ItemRegistry.RADIANCE_INGOTS);
        simpleItem(ItemRegistry.BLUE_GLOW_BERRIES);
        simpleItem(ItemRegistry.NOCTILURE_TREAT);
        simpleItem(ItemRegistry.ABYSS_MUSIC_DISC);
        simpleItem(ItemRegistry.DEEP_FOREST_MUSIC_DISC);
        simpleItem(ItemRegistry.WHITE_FOREST_MUSIC_DISC);
        simpleItem(ItemRegistry.INCARNATION_MUSIC_DISC);
        
        //Blocks that use their item model when in hand rather than the block model
        blockItemWithItemModel(BlockRegistry.DEEP_CRISTAL);
        blockItemWithItemModel(BlockRegistry.RADIANCE_CRISTAL);
        blockItemWithItemModel(BlockRegistry.DARKTREE_SAPLING);
        blockItemWithItemModel(BlockRegistry.DARKTREE_DOOR);
        blockItemWithItemModel(BlockRegistry.WHITETREE_SAPLING);
        blockItemWithItemModel(BlockRegistry.WHITETREE_DOOR);
        blockItemWithItemModel(BlockRegistry.WHITE_GRASS);
        blockItemWithItemModel(BlockRegistry.TALL_WHITE_GRASS);
        blockItemWithItemModel(BlockRegistry.WHITE_TORCHFLOWER);
        blockItemWithItemModel(BlockRegistry.DEEPFLOWER);
        blockItemWithItemModel(BlockRegistry.BLUE_BUSH);
        blockItemWithItemModel(BlockRegistry.TALL_DARK_GRASS);
        blockItemWithItemModel(BlockRegistry.DARK_GRASS);
        blockItemWithItemModel(BlockRegistry.PROTECTOR_INVOKER);
        blockItemWithItemModel(BlockRegistry.CITRINE);
        blockItemWithItemModel(BlockRegistry.ECHO_CRISTAL);
        blockItemWithItemModel(BlockRegistry.RUBY);
        blockItemWithItemModel(BlockRegistry.MALACHITE);
        blockItemWithItemModel(BlockRegistry.PALE_CRISTAL);
        blockItemWithItemModel(BlockRegistry.CONTAGION_INCARNATION_SKULL);
        
        //Blocks that did not create their own item model in the blockstate generator
        withExistingParent(BlockRegistry.DARKTREE_WOOD.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/darktree_wood"));
        withExistingParent(BlockRegistry.STRIPPED_DARKTREE_WOOD.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/stripped_darktree_wood"));
        withExistingParent(BlockRegistry.STRIPPED_DARKTREE_LOG.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/stripped_darktree_log"));
        withExistingParent(BlockRegistry.DARKTREE_LOG.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/darktree_log"));
        withExistingParent(BlockRegistry.DARKTREE_STAIR.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/darktree_stair"));
        withExistingParent(BlockRegistry.DARKTREE_SLAB.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/darktree_slab"));
        withExistingParent(BlockRegistry.DARKTREE_PRESSURE_PLATE.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/darktree_pressure_plate"));
        withExistingParent(BlockRegistry.DARKTREE_FENCE_GATE.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/darktree_fence_gate"));
        
        withExistingParent(BlockRegistry.WHITETREE_WOOD.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/whitetree_wood"));
        withExistingParent(BlockRegistry.STRIPPED_WHITETREE_WOOD.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/stripped_whitetree_wood"));
        withExistingParent(BlockRegistry.STRIPPED_WHITETREE_LOG.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/stripped_whitetree_log"));
        withExistingParent(BlockRegistry.WHITETREE_LOG.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/whitetree_log"));
        withExistingParent(BlockRegistry.WHITETREE_STAIR.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/whitetree_stair"));
        withExistingParent(BlockRegistry.WHITETREE_SLAB.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/whitetree_slab"));
        withExistingParent(BlockRegistry.WHITETREE_PRESSURE_PLATE.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/whitetree_pressure_plate"));
        withExistingParent(BlockRegistry.WHITETREE_FENCE_GATE.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/whitetree_fence_gate"));

        withExistingParent(BlockRegistry.RADIANCE_CATALYST.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/radiance_catalyst"));
        withExistingParent(BlockRegistry.CHISELED_ABYSSALITE.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/chiseled_abyssalite"));
        withExistingParent(BlockRegistry.ABYSSALITE_BRICKS_STAIRS.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/abyssalite_bricks_stair"));
        withExistingParent(BlockRegistry.ABYSSALITE_BRICKS_SLAB.getId().getPath(),
        		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/abyssalite_bricks_slab"));
        withExistingParent(BlockRegistry.DYSFUNCTIONNING_CATALYST.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/dysfunctionning_catalyst"));
        withExistingParent(BlockRegistry.BLACK_LANTERN.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/black_lantern"));
        withExistingParent(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_STAIR.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/cracked_abyssalite_bricks_stair"));
        withExistingParent(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_SLAB.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/cracked_abyssalite_bricks_slab"));

        //Blocks that use custom methods
        fenceItem(BlockRegistry.DARKTREE_FENCE, BlockRegistry.DARKTREE_PLANKS);
        buttonItem(BlockRegistry.DARKTREE_BUTTON, BlockRegistry.DARKTREE_PLANKS);
        trapdoorItem(BlockRegistry.DARKTREE_TRAPDOOR);
        fenceItem(BlockRegistry.WHITETREE_FENCE, BlockRegistry.WHITETREE_PLANKS);
        buttonItem(BlockRegistry.WHITETREE_BUTTON, BlockRegistry.WHITETREE_PLANKS);
        trapdoorItem(BlockRegistry.WHITETREE_TRAPDOOR);
        wallItem(BlockRegistry.ABYSSALITE_BRICKS_WALL, BlockRegistry.ABYSSALITE_BRICKS);
        wallItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_WALL, BlockRegistry.CRACKED_ABYSSALITE_BRICKS);
        
    }
    
 
    private ItemModelBuilder blockItemWithItemModel(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"item/" + item.getId().getPath()));
    }
    
    private ItemModelBuilder simpleItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"item/" + item.getId().getPath()));
    }
    
    public void trapdoorItem(DeferredBlock<Block> block) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                modLoc("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }
    
    public void wallItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(DeferredItem<Item> itemRegistryObject) {
        final String MOD_ID = ModMain.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}