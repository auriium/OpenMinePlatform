package xyz.auriium.openmineplatform.api.binding.location;

import org.jetbrains.annotations.Nullable;

public interface PlatformLocation {

    long getX();
    long getY();
    long getZ();

    @Nullable Long getPitch();
    @Nullable Long getYaw();

}
