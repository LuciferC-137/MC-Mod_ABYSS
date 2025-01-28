package wardentools.weather.lightning;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AbyssLightningRenderState extends EntityRenderState {
    public long seed;

    public AbyssLightningRenderState() {
    }
}
