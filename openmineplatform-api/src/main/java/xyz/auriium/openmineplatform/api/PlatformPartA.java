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

public class PlatformPartA {

    private final PlatformProjectIdentity identity;
    private final PlatformLocation location;
    private final InterfaceableRegistry registry;
    private final ServiceRegistry serviceRegistry;
    private final Scheduler scheduler;
    private final Colorer colorer;
    private final Logger logger;

    public PlatformPartA(PlatformProjectIdentity identity, PlatformLocation location, InterfaceableRegistry registry, ServiceRegistry serviceRegistry, Scheduler scheduler, Colorer colorer, Logger logger) {
        this.identity = identity;
        this.location = location;
        this.registry = registry;
        this.serviceRegistry = serviceRegistry;
        this.scheduler = scheduler;
        this.colorer = colorer;
        this.logger = logger;
    }

    public PlatformProjectIdentity getIdentity() {
        return identity;
    }

    public PlatformLocation getLocation() {
        return location;
    }

    public InterfaceableRegistry interfaceables() {
        return registry;
    }

    public Scheduler scheduler() {
        return scheduler;
    }

    public Colorer colorer() {
        return colorer;
    }

    public Logger logger() {
        return logger;
    }

    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }
}
