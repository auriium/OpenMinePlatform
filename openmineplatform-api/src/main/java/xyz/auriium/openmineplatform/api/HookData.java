package xyz.auriium.openmineplatform.api;

import java.util.Set;

public interface HookData {

    HookData addPlatformHook(PlatformHook hook);
    HookData addServiceHook(ServiceHook<?> hook);

    <T> void insert(Platform platform);

    static HookData make() {
        return new HookDataImpl();
    }

}
