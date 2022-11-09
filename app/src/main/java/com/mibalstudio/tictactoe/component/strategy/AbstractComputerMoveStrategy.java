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

package com.mibalstudio.tictactoe.component.strategy;

import com.mibalstudio.tictactoe.component.ComputerMoveStrategy;
import com.mibalstudio.tictactoe.model.game.Cell;
import com.mibalstudio.tictactoe.model.game.GameTable;
import com.mibalstudio.tictactoe.model.game.Sign;

import java.util.Random;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public abstract class AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    private final int expectedCountEmptyCells;

    public AbstractComputerMoveStrategy(final int countEmptyCells) {
        this.expectedCountEmptyCells = countEmptyCells;
    }

    @Override
    public final boolean tryToMakeMove(final GameTable gameTable, final Sign moveSign) {
        final Sign findSign = getFindSign(moveSign);
        BestCells bestCells = new BestCells();

        findBestCellForMoveByRows(gameTable, findSign, bestCells);
        findBestCellForMoveByCols(gameTable, findSign, bestCells);
        findBestCellForMoveByMainDiagonal(gameTable, findSign, bestCells);
        findBestCellForMoveBySecondaryDiagonal(gameTable, findSign, bestCells);

        if (bestCells.count > 0) {
            final Cell randomCell = bestCells.emptyCells[new Random().nextInt(bestCells.count)];
            gameTable.setSign(randomCell, moveSign);
            return true;
        } else {
            return false;
        }

        /*
        boolean[] methods = {
                tryToMakeMoveByRows(gameTable, findSign, moveSign),
                tryToMakeMoveByCols(gameTable, findSign, moveSign),
                tryToMakeMoveByMainDiagonal(gameTable, findSign, moveSign),
                tryToMakeMoveBySecondaryDiagonal(gameTable, findSign, moveSign)
        };
        int[] random = getRandomIndexes(4);
        if (methods[random[0]]) {
            return true;
        }
        if (methods[random[1]]) {
            return true;
        }
        if (methods[random[2]]) {
            return true;
        }
        return methods[random[3]];
         */
    }
/*
    private static int[] getRandomIndexes(final int bound) {
        Random random = new Random();
        int[] out = {-1, -1, -1, -1};
        for (int i = 0; i < bound; i++) {
            while (true) {
                int a = random.nextInt(bound);
                if (!arrayDoesNotContains(out, a)) {
                    out[i] = a;
                    break;
                }
            }
        }
        return out;
    }

    private static boolean arrayDoesNotContains(final int[] out, final int a) {
        for (final int num : out) {
            if (num == a) {
                return true;
            }
        }
        return false;
    }

 */

    protected abstract Sign getFindSign(Sign moveSign);

    @SuppressWarnings("Convert2MethodRef")
    private void findBestCellForMoveByRows(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        for (int i = 0; i < 3; i++) {
            findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, i, (k, j) -> new Cell(k, j));
        }
    }

    private void findBestCellForMoveByCols(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        for (int i = 0; i < 3; i++) {
            findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, i, (k, j) -> new Cell(j, k));
        }
    }

    private void findBestCellForMoveByMainDiagonal(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, -1, (k, j) -> new Cell(j, j));
    }

    private void findBestCellForMoveBySecondaryDiagonal(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, -1, (k, j) -> new Cell(j, 2 - j));
    }

    private void findBestCellForMoveUsingLambdaConversion(GameTable gameTable,
                                                          Sign findSign,
                                                          final BestCells bestCells,
                                                          final int i,
                                                          final Lambda lambda) {
        int countEmptyCells = 0;
        int countSignCells = 0;
        final Cell[] localEmptyCells = new Cell[3];
        int count = 0;
        for (int j = 0; j < 3; j++) {
            final Cell cell = lambda.conversion(i, j);
            if (gameTable.isEmpty(cell)) {
                localEmptyCells[count++] = cell;
                countEmptyCells++;
            } else if (gameTable.getSign(cell) == findSign) {
                countSignCells++;
            } else {
                break;
            }
        }
        if (countEmptyCells == expectedCountEmptyCells &&
            countSignCells == 3 - expectedCountEmptyCells) {
            for (int j = 0; j < count; j++) {
                bestCells.add(localEmptyCells[j]);
            }
        }
    }

    /**
     * @author Michael Balakhon
     * @link http://t.me/mibal_ua
     */
    @FunctionalInterface
    private interface Lambda {

        Cell conversion(int k, int j);
    }

    /**
     * @author Michael Balakhon
     * @link http://t.me/mibal_ua
     */
    private static class BestCells {

        private final Cell[] emptyCells = new Cell[9];

        private int count;

        private void add(final Cell cell) {
            emptyCells[count++] = cell;
        }
    }
}
