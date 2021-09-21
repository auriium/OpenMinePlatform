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

package xyz.auriium.openmineplatform.api.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CommonPlatformServiceRegistry implements PlatformServiceRegistry {

    private final Map<Class<?>, Service<?>> map = new ConcurrentHashMap<>();

    @Override
    public <T> void register(Class<T> service, T provider, String name) {
        map.put(service, new Service<>(provider, name));
    }

    @Override
    public <T> Optional<T> retrieve(Class<T> service) {
        return retrieveService(service).map(Service::getService);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<Service<T>> retrieveService(Class<T> service) {
        return Optional.ofNullable((Service<T>)map.get(service));
    }

    @Override
    public boolean unregister(Class<?> clazz) {
        return map.remove(clazz) != null;
    }

    @Override
    public <T> boolean hasService(Class<T> service) {
        return map.containsKey(service);
    }
}
