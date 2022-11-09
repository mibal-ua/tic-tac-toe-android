/*
 * Copyright 2022 http://t.me/mibal_ua
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mibalstudio.tictactoe.component;

import com.mibalstudio.tictactoe.model.game.GameTable;
import com.mibalstudio.tictactoe.model.game.Sign;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class ComputerMove implements Move {

    private final ComputerMoveStrategy[] strategies;

    public ComputerMove(final ComputerMoveStrategy[] strategies) {
        this.strategies = strategies;
    }

    @Override
    public void make(final GameTable gameTable, final Sign sign) {
        for (final ComputerMoveStrategy strategy : strategies) {
            if (strategy.tryToMakeMove(gameTable, sign)) {
                return;
            }
        }
        throw new IllegalArgumentException(
                "Game table does not contain empty cells or invalid configuration for computer strategies."
        );
    }
}
