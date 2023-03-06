package com.mibalstudio.tictactoe.component.config;

import com.mibalstudio.tictactoe.model.config.Level;
import com.mibalstudio.tictactoe.model.config.PlayerType;
import com.mibalstudio.tictactoe.model.config.UserInterface;

import java.io.Serializable;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class CommandLineArguments implements Serializable {

    private final PlayerType player1Type;

    private final PlayerType player2Type;

    private final UserInterface userInterface;

    private final Level level;

    private CommandLineArguments(final PlayerType player1Type,
                                 final PlayerType player2Type,
                                 final UserInterface userInterface,
                                 final Level level) {
        this.player1Type = player1Type;
        this.player2Type = player2Type;
        this.userInterface = userInterface;
        this.level = level;
    }

    public PlayerType getPlayer1Type() {
        return player1Type;
    }

    public PlayerType getPlayer2Type() {
        return player2Type;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public Level getLevel() {
        return level;
    }
}
