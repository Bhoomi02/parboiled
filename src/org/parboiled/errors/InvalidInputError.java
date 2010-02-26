/*
 * Copyright (C) 2009 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.errors;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.parboiled.support.InputLocation;
import org.parboiled.support.MatcherPath;

import java.util.List;

public class InvalidInputError<V> extends SimpleParseError {

    private final MatcherPath<V> lastMatch;
    private final List<MatcherPath<V>> failedMatchers;
    private int errorCharCount = 1;

    public InvalidInputError(@NotNull InputLocation errorLocation, MatcherPath<V> lastMatch,
                             @NotNull List<MatcherPath<V>> failedMatchers, String errorMessage) {
        super(errorLocation, errorMessage);
        this.lastMatch = lastMatch;
        this.failedMatchers = failedMatchers;
    }

    @Override
    public int getErrorCharCount() {
        return errorCharCount;
    }

    public void setErrorCharCount(int errorCharCount) {
        Preconditions.checkArgument(errorCharCount > 0);
        this.errorCharCount = errorCharCount;
    }

    public MatcherPath<V> getLastMatch() {
        return lastMatch;
    }

    @NotNull
    public List<MatcherPath<V>> getFailedMatchers() {
        return failedMatchers;
    }

}
