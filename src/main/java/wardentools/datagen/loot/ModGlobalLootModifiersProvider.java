package wardentools.datagen.loot;


import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import wardentools.ModMain;


public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, ModMain.MOD_ID);
    }

    @Override
    protected void start() {
    	
       
    }
}