package xyz.auriium.openmineplatform.api.telescope;

public class ExceptionalMapper<I,O> implements TelescopeMapping<O,I> {

    private final Telescope<I,O> telescope;

    public ExceptionalMapper(Telescope<I, O> telescope) {
        this.telescope = telescope;
    }

    @Override
    public O calculate(I input) {
        var result = telescope.telescope(input);
        if (!result.isSuccess()) throw new BadTelescopeException(result.getError());

        return result.getCompletion();
    }
}
