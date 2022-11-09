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
import com.mibalstudio.tictactoe.model.game.GameTable;

import com.mibalstudio.tictactoe.model.game.Player;
import com.mibalstudio.tictactoe.model.game.Sign;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class WinnerVerifier {

    public boolean isWinner(GameTable gameTable, Player player) {
        return isWinnerByRows(gameTable, player.getSign()) ||
               isWinnerByCols(gameTable, player.getSign()) ||
               isWinnerByMainDiagonal(gameTable, player.getSign()) ||
               isWinnerBySecondDiagonal(gameTable, player.getSign());
    }

    private boolean isWinnerByRows(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            if (gameTable.getSign(new Cell(i, 0)) == gameTable.getSign(new Cell(i, 1)) &&
                gameTable.getSign(new Cell(i, 1)) == gameTable.getSign(new Cell(i, 2)) &&
                gameTable.getSign(new Cell(i, 2)) == sign) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinnerByCols(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            if (gameTable.getSign(new Cell(0, i)) == gameTable.getSign(new Cell(1, i)) &&
                gameTable.getSign(new Cell(1, i)) == gameTable.getSign(new Cell(2, i)) &&
                gameTable.getSign(new Cell(2, i)) == sign) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinnerByMainDiagonal(final GameTable gameTable, final Sign sign) {
        return gameTable.getSign(new Cell(0, 0)) == gameTable.getSign(new Cell(1, 1)) &&
               gameTable.getSign(new Cell(1, 1)) == gameTable.getSign(new Cell(2, 2)) &&
               gameTable.getSign(new Cell(2, 2)) == sign;
    }

    private boolean isWinnerBySecondDiagonal(final GameTable gameTable, final Sign sign) {
        return gameTable.getSign(new Cell(2, 0)) == gameTable.getSign(new Cell(1, 1)) &&
               gameTable.getSign(new Cell(1, 1)) == gameTable.getSign(new Cell(0, 2)) &&
               gameTable.getSign(new Cell(0, 2)) == sign;
    }

}
