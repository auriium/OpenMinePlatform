package xyz.auriium.openmineplatform.api;

import java.util.HashSet;
import java.util.Set;

public class HookDataImpl implements HookData {

    private final Set<PlatformHook> platformHooks = new HashSet<>();
    private final Set<ServiceHook<?>> serviceHooks = new HashSet<>();

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
    public void insert(Platform platform) {
        for (PlatformHook hook : platformHooks) {
            hook.execute(platform);
        }

        for (ServiceHook<?> hook : serviceHooks) {
            /// FIXME: 8/25/2021 safer cast here

            platform.serviceRegistry().register((Class)hook.providedServiceType(), hook.provide(platform), hook.name());
        }
    }


}
