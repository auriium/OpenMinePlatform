package xyz.auriium.openmineplatform.api.binding.location;

import org.jetbrains.annotations.Nullable;

public class UnboundPlatformLocation implements PlatformLocation {

    private final long x;
    private final long y;
    private final long z;
    private final Long pitch;
    private final Long yaw;

    private final String world;

    public UnboundPlatformLocation(long x, long y, long z, Long pitch, Long yaw, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.world = world;
    }


    @Nullable
    public String getWorld() {
        return world;
    }

    @Override
    public long getX() {
        return x;
    }

    @Override
    public long getY() {
        return y;
    }

    @Override
    public long getZ() {
        return z;
    }

    @Nullable
    @Override
    public Long getPitch() {
        return pitch;
    }

    @Override
    public Long getYaw() {
        return yaw;
    }

    public static PlatformLocation of(long x, long y, long z) {
        return new UnboundPlatformLocation(x,y,z, null, null ,null);
    }

    public static PlatformLocation of(long x, long y, long z, Long pitch, Long yaw) {
        return new UnboundPlatformLocation(x, y, z, pitch, yaw, null);
    }

    public static PlatformLocation of(long x, long y, long z, String world) {
        return new UnboundPlatformLocation(x, y, z, null, null, world);
    }

    public static PlatformLocation of(long x, long y, long z, Long pitch, Long yaw, String world) {
        return new UnboundPlatformLocation(x, y, z, pitch, yaw, world);
    }

}
