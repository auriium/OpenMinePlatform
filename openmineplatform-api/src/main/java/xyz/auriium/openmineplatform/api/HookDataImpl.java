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
