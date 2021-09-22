/*
 * OpenMinePlatform
 * Copyright © 2021 Auriium
 *
 * OpenMinePlatform is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * OpenMinePlatform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenMinePlatform. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

package xyz.auriium.openmineplatform.api.binding.location;

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


    @Override
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
