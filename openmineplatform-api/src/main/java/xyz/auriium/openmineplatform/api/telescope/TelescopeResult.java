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
