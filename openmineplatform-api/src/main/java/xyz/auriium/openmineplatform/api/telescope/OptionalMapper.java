package xyz.auriium.openmineplatform.api.telescope;

import java.util.Optional;

public class OptionalMapper<I,O> implements TelescopeMapping<Optional<O>, I> {

    private final Telescope<I,O> telescope;

    public OptionalMapper(Telescope<I, O> telescope) {
        this.telescope = telescope;
    }

    @Override
    public Optional<O> calculate(I input) {
        var result = telescope.telescope(input);

        if (!result.isSuccess()) return Optional.empty();

        return Optional.of(result.getCompletion());
    }

}
