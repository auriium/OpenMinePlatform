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

import org.slf4j.Logger;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.InterfaceableRegistry;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;
import xyz.auriium.openmineplatform.api.service.ServiceRegistry;

public abstract class AbstractPlatform implements Platform {

    private final PlatformPartA platformPartA;

    public AbstractPlatform(PlatformPartA platformPartA) {
        this.platformPartA = platformPartA;
    }

    @Override
    public PlatformProjectIdentity getIdentity() {
        return platformPartA.getIdentity();
    }

    @Override
    public PlatformLocation getLocation() {
        return platformPartA.getLocation();
    }

    @Override
    public InterfaceableRegistry interRegistry() {
        return platformPartA.interfaceables();
    }

    @Override
    public Scheduler scheduler() {
        return platformPartA.scheduler();
    }

    @Override
    public Colorer colorer() {
        return platformPartA.colorer();
    }

    @Override
    public Logger logger() {
        return platformPartA.logger();
    }

    @Override
    public ServiceRegistry serviceRegistry() {
        return platformPartA.getServiceRegistry();
    }
}
