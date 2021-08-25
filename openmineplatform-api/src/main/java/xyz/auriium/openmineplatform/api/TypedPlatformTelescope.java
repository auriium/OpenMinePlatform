package xyz.auriium.openmineplatform.api;

public class TypedPlatformTelescope<T> implements Telescope<Platform, TypedPlatform<T>> {

    private final Class<T> clazz;

    TypedPlatformTelescope(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public TelescopeResult<Platform, TypedPlatform<T>> telescope(Platform platform) {

        if (!(platform instanceof TypedPlatform)) return TelescopeResult.fail(platform, "Platform is not instance of TypedPlatform!");

        var s = (TypedPlatform<?>) platform;

        if (!clazz.isAssignableFrom(s.getBoundPlatform().getClass())) return TelescopeResult.fail(platform, "Platform does not have same type or inherited type as requested TypePlatform cast!");

        return TelescopeResult.complete(platform, (TypedPlatform<T>) s);
    }

    public static <T> TypedPlatformTelescope<T> of(Class<T> requestedPlatformType) {
        return new TypedPlatformTelescope<>(requestedPlatformType);
    }
}
