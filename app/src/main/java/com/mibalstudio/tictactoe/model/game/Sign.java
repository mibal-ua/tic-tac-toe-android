/*
 * Copyright (c) 2022. http://t.me/mibal_ua
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mibalstudio.tictactoe.model.game;

import com.mibalstudio.tictactoe.R;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public enum Sign {

    X(R.drawable.cross),

    O(R.drawable.circle),

    EMPTY(R.drawable.empty);

    private final int resourceId;

    Sign(final int resourceId) {
        this.resourceId = resourceId;
    }

    public Sign oppositeSign() {
        if (this == X) {
            return O;
        } else if (this == O) {
            return X;
        } else {
            throw new IllegalStateException("Empty value does not have an opposite one.");
        }
    }

    public int getResourceId() {
        return resourceId;
    }

    @Override
    public String toString() {
        if (this == EMPTY) {
            return " ";
        } else {
            return name();
        }
    }
}
