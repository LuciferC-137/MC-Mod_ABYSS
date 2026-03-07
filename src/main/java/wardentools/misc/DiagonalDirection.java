package wardentools.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public enum DiagonalDirection {
    NORTHEAST(0, Direction.NORTH, Direction.EAST),
    SOUTHEAST(1, Direction.EAST, Direction.SOUTH),
    SOUTHWEST(2, Direction.SOUTH, Direction.WEST),
    NORTHWEST(3, Direction.WEST, Direction.NORTH);

    static final HashMap<Integer, DiagonalDirection> indexMap = new HashMap<>();

    static {
        for (DiagonalDirection dir : DiagonalDirection.values()) {
            indexMap.put(dir.index, dir);
        }
    }

    private final int index;
    private final Direction dir1;
    private final Direction dir2;

    DiagonalDirection(int index, Direction dir1, Direction dir2) {
        this.index = index;
        this.dir1 = dir1;
        this.dir2 = dir2;
    }

    public Direction getDir1() {
        return this.dir1;
    }

    public Direction getDir2() {
        return dir2;
    }

    public int getStepX() {
        return this.dir1.getStepX() + this.dir2.getStepX();
    }

    public int getStepY() {
        return this.dir1.getStepZ() + this.dir2.getStepZ();
    }

    public int getStepZ() {
        return this.dir1.getStepZ() + this.dir2.getStepZ();
    }

    public BlockPos apply(BlockPos pos) {
        return pos.offset(this.getStepX(), 0, this.getStepZ());
    }

    public boolean has(Direction direction) {
        return this.dir1 == direction || this.dir2 == direction;
    }

    public DiagonalDirection opposite() {
        return DiagonalDirection.byId((this.index + 2) % 4);
    }

    public static DiagonalDirection byId(int i) {
        return indexMap.get(i);
    }

    public static DiagonalDirection random(RandomSource random) {
        return DiagonalDirection.byId(random.nextIntBetweenInclusive(0, 3));
    }

    public static HashSet<DiagonalDirection> randomSet(RandomSource random) {
        HashSet<DiagonalDirection> set = new HashSet<>();
        for (DiagonalDirection dir : DiagonalDirection.values()) {
            if (random.nextBoolean()) {
                set.add(dir);
            }
        }
        return set;
    }

    public static Set<DiagonalDirection> randomSet(RandomSource random, int nbValues) {
        if (nbValues == 3) {
            HashSet<DiagonalDirection> set = new HashSet<>(Arrays.stream(DiagonalDirection.values()).toList());
            set.remove(DiagonalDirection.random(random));
            return Set.copyOf(set);
        }
        if (nbValues == 2) {
            HashSet<DiagonalDirection> set = new HashSet<>();
            while (set.size() < 2) {
                set.add(DiagonalDirection.random(random));
            }
            return Set.copyOf(set);
        }
        if (nbValues == 1) {
            return Set.of(DiagonalDirection.random(random));
        }
        return Set.copyOf(Arrays.stream(DiagonalDirection.values()).toList());
    }

    public static Set<DiagonalDirection> randomOppositeSet(RandomSource random) {
        DiagonalDirection dir = DiagonalDirection.random(random);
        DiagonalDirection oppositeDir = dir.opposite();
        return Set.of(dir, oppositeDir);
    }
}
