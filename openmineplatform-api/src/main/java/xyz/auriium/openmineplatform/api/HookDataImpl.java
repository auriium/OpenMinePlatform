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

package xyz.auriium.openmineplatform.api;

import java.util.ArrayList;
import java.util.List;

public class HookDataImpl implements HookData {

    private final List<PlatformHook> platformHooks = new ArrayList<>();
    private final List<ServiceHook<?>> serviceHooks = new ArrayList<>();

    HookDataImpl() {}

    @Override
    public HookData addPlatformHook(PlatformHook hook) {
        this.platformHooks.add(hook);

        return this;
    }

    @Override
    public HookData addServiceHook(ServiceHook<?> hook) {
        this.serviceHooks.add(hook);

        return this;
    }

    @Override
    public void insert(Platform platform, boolean startup) {
        for (PlatformHook hook : platformHooks) {
            if (startup == hook.startsBeforePlugin()) {
                hook.execute(platform);
            }

        }

        for (ServiceHook<?> hook : serviceHooks) {
            /// FIXME: 8/25/2021 safer cast here
            if (startup == hook.startsBeforePlugin()) {
                platform.serviceRegistry().register((Class)hook.providedServiceType(), hook.provide(platform), hook.name());
            }
        }
    }


}
