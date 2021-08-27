package xyz.auriium.openmineplatform.api.telescope;

public class TelescopeResult<I,O> {

    private final I input;
    private final O completion;
    private final String error;

    TelescopeResult(I input, O completion, String error) {
        this.input = input;
        this.completion = completion;
        this.error = error;
    }

    public boolean isSuccess() {
        return error == null && completion != null;
    }

    public I getInput() {
        return input;
    }

    public O getCompletion() {
        if (!isSuccess()) {
            throw new IllegalStateException("Result is not successful and therefore is incomplete!");
        }

        return completion;
    }

    public String getError() {
        if (isSuccess()) {
            throw new IllegalStateException("Result is successful and therefore has no error!");
        }

        return error;
    }

    public static <I,T> TelescopeResult<I,T> complete(I input, T value) {
        return new TelescopeResult<>(input, value, null);
    }

    public static <I,T> TelescopeResult<I,T> fail(I input, String fail) {
        return new TelescopeResult<>(input, null, fail);
    }
}
