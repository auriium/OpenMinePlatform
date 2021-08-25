package xyz.auriium.openmineplatform.api;

import xyz.auriium.openmineplatform.api.TelescopeResult;

/**
 * Represents a safe cast or conversion
 * @param <I> input object
 * @param <O> output object
 */
public interface Telescope<I,O> {

    TelescopeResult<I,O> telescope(I input);

}
