package wardentools.worldgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class CrystalTemple extends Structure {

    public static final MapCodec<CrystalTemple> MAP_CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Structure.settingsCodec(instance),
                    ResourceKey.codec(Registries.TEMPLATE_POOL).fieldOf("start_pool").forGetter(s -> s.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(s -> s.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(s -> s.maxDepth),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(s -> s.maxDistanceFromCenter)
            ).apply(instance, CrystalTemple::new)
    );

    public static final Codec<CrystalTemple> CODEC = MAP_CODEC.codec();

    private final ResourceKey<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int maxDepth;
    private final int maxDistanceFromCenter;

    public CrystalTemple(StructureSettings settings,
                         ResourceKey<StructureTemplatePool> startPool,
                         Optional<ResourceLocation> startJigsawName,
                         int maxDepth,
                         int maxDistanceFromCenter) {
        super(settings);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.maxDepth = maxDepth;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        int x = chunkPos.getMiddleBlockX();
        int z = chunkPos.getMiddleBlockZ();

        TagKey<Biome> undergroundBiomeTag = TagKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
                        "has_structure/has_amethyst_temple")
        );

        if (!biomeColumnCheck(context, undergroundBiomeTag)) {
            return Optional.empty();
        }

        int y = context.chunkGenerator().getFirstFreeHeight(
                x,
                z,
                Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(),
                context.randomState()
        );

        BlockPos pos = new BlockPos(x, y, z);

        Registry<StructureTemplatePool> poolRegistry = context.registryAccess()
                .registryOrThrow(Registries.TEMPLATE_POOL);

        Optional<Holder.Reference<StructureTemplatePool>> poolHolder =
                poolRegistry.getHolder(this.startPool);

        return poolHolder.flatMap(structureTemplatePoolReference -> JigsawPlacement.addPieces(
                context,
                structureTemplatePoolReference,
                this.startJigsawName,
                this.maxDepth,
                pos,
                false,
                Optional.empty(),
                this.maxDistanceFromCenter,
                PoolAliasLookup.EMPTY,
                DimensionPadding.ZERO,
                LiquidSettings.APPLY_WATERLOGGING
        ));

    }

    private static boolean biomeColumnCheck(GenerationContext context, TagKey<Biome> tag) {
        ChunkPos chunkPos = context.chunkPos();
        int x = chunkPos.getMiddleBlockX();
        int z = chunkPos.getMiddleBlockZ();
        boolean hasBiomeInColumn = false;

        for (int checkY = context.heightAccessor().getMinBuildHeight();
             checkY < context.heightAccessor().getMaxBuildHeight();
             checkY += 32) {

            Holder<Biome> biome = context.chunkGenerator().getBiomeSource().getNoiseBiome(
                    QuartPos.fromBlock(x),
                    QuartPos.fromBlock(checkY),
                    QuartPos.fromBlock(z),
                    context.randomState().sampler()
            );

            if (biome.is(tag)) {
                hasBiomeInColumn = true;
                break;
            }
        }
        return hasBiomeInColumn;
    }

    @Override
    public @NotNull StructureType<CrystalTemple> type() {
        return ModStructureTypes.CRYSTAL_TEMPLE.get();
    }
}

