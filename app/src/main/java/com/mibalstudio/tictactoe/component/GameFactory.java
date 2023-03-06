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

package com.mibalstudio.tictactoe.component;

import static com.mibalstudio.tictactoe.model.config.PlayerType.USER;
import static com.mibalstudio.tictactoe.model.game.Sign.O;
import static com.mibalstudio.tictactoe.model.game.Sign.X;

import com.mibalstudio.tictactoe.GameActivity;
import com.mibalstudio.tictactoe.component.config.CommandLineArguments;
import com.mibalstudio.tictactoe.model.config.Level;
import com.mibalstudio.tictactoe.model.config.PlayerType;
import com.mibalstudio.tictactoe.model.game.Player;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class GameFactory {

    private final PlayerType player2Type;

    private final Level level;

    public GameFactory(final CommandLineArguments commandLineArguments) {
        player2Type = commandLineArguments.getPlayer2Type();
        level = commandLineArguments.getLevel();
    }

    public Game create() {
        final DataPrinter dataPrinter;
        final UserInputReader userInputReader;
        final GameOverHandler gameOverHandler;
        final GameActivity gameActivity = new GameActivity();
        dataPrinter = gameActivity;
        userInputReader = gameActivity;
        gameOverHandler = gameActivity;
        final Player player1 = new Player(X, new UserMove(userInputReader, dataPrinter));
        final Player player2;
        if (player2Type == USER) {
            player2 = new Player(O, new UserMove(userInputReader, dataPrinter));
        } else {
            player2 = new Player(O, new ComputerMove(level.getStrategies()));
        }
        final boolean canSecondPlayerMakeFirstMove = player2Type != USER;
        return new Game(
                dataPrinter,
                player1,
                player2,
                new WinnerVerifier(),
                new CellVerifier(),
                canSecondPlayerMakeFirstMove,
                gameOverHandler
        );
    }
}