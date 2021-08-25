package xyz.auriium.openmineplatform.api.scheduling;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

public interface DelayedPurveyor extends Executor {

    <T> CompletionStage<T> provide(Supplier<T> supplier);

}
