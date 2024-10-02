package wardentools.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.armors.ArmorRegistry;
import wardentools.block.BlockRegistry;
import wardentools.items.ItemRegistry;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
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
        
        //Simple Item
        simpleItem(ItemRegistry.DEEPINGOTS);
        simpleItem(ItemRegistry.WARDEN_HEART);
        simpleItem(ItemRegistry.DEEP_FRUIT);
        simpleItem(ItemRegistry.WIND_WHISPERER);
        simpleItem(ItemRegistry.PALE_FRAGMENT);
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
        
        //Blocks that use their item model when in hand rather than the block model
        blockItemWithItemModel(BlockRegistry.DEEP_CRISTAL);
        blockItemWithItemModel(BlockRegistry.PALE_SHARD);
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
        
        //Blocks that did not created their own item model in the blockstate generator
        withExistingParent(BlockRegistry.DARKTREE_WOOD.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/darktree_wood"));
        withExistingParent(BlockRegistry.STRIPPED_DARKTREE_WOOD.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/stripped_darktree_wood"));
        withExistingParent(BlockRegistry.STRIPPED_DARKTREE_LOG.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/stripped_darktree_log"));
        withExistingParent(BlockRegistry.DARKTREE_LOG.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/darktree_log"));
        withExistingParent(BlockRegistry.DARKTREE_STAIR.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/darktree_stair"));
        withExistingParent(BlockRegistry.DARKTREE_SLAB.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/darktree_slab"));
        withExistingParent(BlockRegistry.DARKTREE_PRESSURE_PLATE.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/darktree_pressure_plate"));
        withExistingParent(BlockRegistry.DARKTREE_FENCE_GATE.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/darktree_fence_gate"));
        
        withExistingParent(BlockRegistry.WHITETREE_WOOD.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/whitetree_wood"));
        withExistingParent(BlockRegistry.STRIPPED_WHITETREE_WOOD.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/stripped_whitetree_wood"));
        withExistingParent(BlockRegistry.STRIPPED_WHITETREE_LOG.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/stripped_whitetree_log"));
        withExistingParent(BlockRegistry.WHITETREE_LOG.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/whitetree_log"));
        withExistingParent(BlockRegistry.WHITETREE_STAIR.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/whitetree_stair"));
        withExistingParent(BlockRegistry.WHITETREE_SLAB.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/whitetree_slab"));
        withExistingParent(BlockRegistry.WHITETREE_PRESSURE_PLATE.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/whitetree_pressure_plate"));
        withExistingParent(BlockRegistry.WHITETREE_FENCE_GATE.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/whitetree_fence_gate"));
        withExistingParent(BlockRegistry.RADIANCE_CATALYST.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/radiance_catalyst"));
        withExistingParent(BlockRegistry.CHISELED_ABYSSALITE.getId().getPath(),
        		new ResourceLocation(ModMain.MOD_ID, "block/chiseled_abyssalite"));
        
        
        //Blocks that use custom methods
        fenceItem(BlockRegistry.DARKTREE_FENCE, BlockRegistry.DARKTREE_PLANKS);
        buttonItem(BlockRegistry.DARKTREE_BUTTON, BlockRegistry.DARKTREE_PLANKS);
        trapdoorItem(BlockRegistry.DARKTREE_TRAPDOOR);
        fenceItem(BlockRegistry.WHITETREE_FENCE, BlockRegistry.WHITETREE_PLANKS);
        buttonItem(BlockRegistry.WHITETREE_BUTTON, BlockRegistry.WHITETREE_PLANKS);
        trapdoorItem(BlockRegistry.WHITETREE_TRAPDOOR);
        
    }
    
 
    private ItemModelBuilder blockItemWithItemModel(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ModMain.MOD_ID,"item/" + item.getId().getPath()));
    }
    
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ModMain.MOD_ID,"item/" + item.getId().getPath()));
    }
    
    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(ModMain.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(ModMain.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = ModMain.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}