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

package xyz.auriium.openmineplatform.spigot.scheduler;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.scheduling.DelayedPurveyor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

public class BukkitUnsafePurveyor implements DelayedPurveyor {
    
    private final JavaPlugin plugin;

    public BukkitUnsafePurveyor(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public <T> CompletionStage<T> provide(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, this); //TODO THIS IS UNSAFE
        /// FIXME: 8/23/2021 MAKE SAFE WITH PAPER
    }

    @Override
    public void execute(Runnable command) {
        plugin.getServer().getScheduler().runTask(plugin, command);
    }
}
