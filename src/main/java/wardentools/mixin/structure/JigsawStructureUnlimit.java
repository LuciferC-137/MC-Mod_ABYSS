package wardentools.mixin.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasBinding;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wardentools.ModMain;

import java.lang.reflect.Field;
import java.util.List;

/*
 Inspired from the mod Huge Structure Block
 https://github.com/SamB440/huge-structure-blocks/tree/neoforge/1.20?tab=readme-ov-file#huge-structure-blocks
*/

@Mixin(JigsawStructure.class)
public abstract class JigsawStructureUnlimit {
    @Unique
    private static final Logger logger = LogManager.getLogger(JigsawStructureUnlimit.class);

    @Shadow
    @Final
    @Mutable
    public static Codec<JigsawStructure> CODEC;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyCodec(CallbackInfo ci) {
        try {
            Field codecField = JigsawStructure.class.getDeclaredField("CODEC");
            codecField.setAccessible(true);

            Codec<JigsawStructure> newCodec = ExtraCodecs.validate(
                    RecordCodecBuilder.mapCodec((instance) -> instance.group(
                            Structure.settingsCodec(instance),
                            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(
                                    (structure) -> getPrivateField(structure, "startPool")),
                            ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(
                                    (structure) -> getPrivateField(structure, "startJigsawName")),
                            Codec.intRange(0, 20).fieldOf("size").forGetter(
                                    (structure) -> getPrivateField(structure, "maxDepth")),
                            HeightProvider.CODEC.fieldOf("start_height").forGetter(
                                    (structure) -> getPrivateField(structure, "startHeight")),
                            Codec.BOOL.fieldOf("use_expansion_hack").forGetter(
                                    (structure) -> getPrivateField(structure, "useExpansionHack")),
                            Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(
                                    (structure) -> getPrivateField(structure, "projectStartToHeightmap")),
                            Codec.intRange(1, ModMain.NEW_STRUCTURE_SIZE).fieldOf("max_distance_from_center")
                                    .forGetter((structure) -> getPrivateField(structure, "maxDistanceFromCenter")),
                            Codec.list(PoolAliasBinding.CODEC).optionalFieldOf("pool_aliases", List.of())
                                    .forGetter((structure) -> getPrivateField(structure, "poolAliases"))
                    ).apply(instance, JigsawStructure::new)),
                    JigsawStructureUnlimit::verifyRange
            ).codec();

            codecField.set(null, newCodec);
            logger.info("Mixin successfully modified JigsawStructure.CODEC for bigger structures.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Failed to update CODEC field", e);
        }
    }

    @Unique
    @SuppressWarnings("unchecked")
    private static <T> T getPrivateField(Object instance, String fieldName) {
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(instance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to access field: " + fieldName, e);
        }
    }


    @Unique
    private static DataResult<JigsawStructure> verifyRange(JigsawStructure structure) {
        try {
            Field maxDistanceField = JigsawStructure.class.getDeclaredField("maxDistanceFromCenter");
            maxDistanceField.setAccessible(true);
            int maxDistanceFromCenter = maxDistanceField.getInt(structure);
            if (maxDistanceFromCenter > ModMain.NEW_STRUCTURE_SIZE) {
                return DataResult.error(() -> "maxDistanceFromCenter is too large: " + maxDistanceFromCenter);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Failed to verify maxDistanceFromCenter: {}", e.getMessage(), e);
            return DataResult.error(() -> "Failed to verify maxDistanceFromCenter: " + e.getMessage());
        }
        return DataResult.success(structure);
    }
}

