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

import com.mibalstudio.tictactoe.model.game.Cell;

import static java.lang.String.format;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class CellToNumberConverter implements CellNumberConverter {

    @Override
    public Cell toCell(final char num) {
        if ('1' <= num && num <= '9') {
            final int val = num - '0' - 1;
            return new Cell(2 - val / 3, val % 3);
        } else {
            throw new IllegalArgumentException(
                    format("Number parameter must be between '1' and '9' . Current value is '%s'", num)
            );
        }
    }

    @Override
    public char toNumber(final Cell cell) {
        if (0 <= cell.getRow() && cell.getRow() <= 2 && 0 <= cell.getRow() && cell.getRow() <= 2) {
            return (char) ('0' + ((2 - cell.getRow()) * 3 + cell.getCol() + 1));
        } else {
            throw new IllegalArgumentException(
                    format("Row and col indexes must be between 0 and 2. Current row is '%s', current col is '%s'", cell.getRow(), cell.getCol())
            );
        }
    }
}
