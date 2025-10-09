package wardentools.client.color;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple cache for biome colors to avoid recalculating them every frame.
 * Each entry expires after a set number of ticks.
 */
@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, value = Dist.CLIENT)
public class AbyssBiomeColorCache {

    private static final Map<Long, CacheEntry> CACHE = new ConcurrentHashMap<>();

    private static final int CACHE_LIFETIME = 120;
    // Cache can be larger because cleanup is only CLEANUP_BATCH every CLEANUP_INTERVAL ticks
    private static final int MAX_CACHE_SIZE = 40000;
    private static final int CLEANUP_INTERVAL = 20;
    private static final int CLEANUP_BATCH = 5000;

    private static long lastCleanupTick = 0;

    private static record CacheEntry(int color, long expireTick) {}

    public static Integer get(BlockPos pos, int index) {
        long key = makeKey(pos, index);
        CacheEntry entry = CACHE.get(key);
        if (entry == null) return null;

        long currentTick = getClientTick();
        if (entry.expireTick <= currentTick) {
            CACHE.remove(key);
            return null;
        }

        return entry.color;
    }

    public static void put(BlockPos pos, int index, int color) {
        long key = makeKey(pos, index);
        long expire = getClientTick() + CACHE_LIFETIME;
        CACHE.put(key, new CacheEntry(color, expire));
    }

    @SubscribeEvent
    public static void clearOnPlayerNetwork(ClientPlayerNetworkEvent event) {
        clear();
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        long tick = getClientTick();
        if (tick - lastCleanupTick < CLEANUP_INTERVAL) return;

        lastCleanupTick = tick;
        cleanupBatch();
    }

    private static void cleanupBatch() {
        long currentTick = getClientTick();

        int removed = 0;
        Iterator<Map.Entry<Long, CacheEntry>> it = CACHE.entrySet().iterator();
        while (it.hasNext() && removed < CLEANUP_BATCH) {
            Map.Entry<Long, CacheEntry> e = it.next();
            if (e.getValue().expireTick <= currentTick) {
                it.remove();
                removed++;
            }
        }

        // If the cache is still too large, do a full cleanup
        // TODO: use a client config option for this
        if (CACHE.size() > MAX_CACHE_SIZE * 1.5) {
            System.out.println("[AbyssBiomeColorCache] Forcing full cleanup: " + CACHE.size());
            CACHE.entrySet().removeIf(e -> e.getValue().expireTick <= currentTick);
        }
    }

    public static void clear() {
        CACHE.clear();
    }

    private static long makeKey(BlockPos pos, int index) {
        return pos.asLong() ^ ((long) index << 48);
    }

    private static long getClientTick() {
        Minecraft mc = Minecraft.getInstance();
        return mc.level != null ? mc.level.getGameTime() : 0;
    }
}


