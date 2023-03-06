package com.mibalstudio.tictactoe.component.config;

import com.mibalstudio.tictactoe.model.config.Level;
import com.mibalstudio.tictactoe.model.config.PlayerType;

import java.io.Serializable;

/**
 * @author Michael Balakhon
 * @link http://t.me/mibal_ua
 */
public class CommandLineArguments implements Serializable {

    private final PlayerType player2Type;

    private final Level level;

    public CommandLineArguments(final PlayerType player2Type,
                                final Level level) {
        this.player2Type = player2Type;
        this.level = level;
    }

    public PlayerType getPlayer2Type() {
        return player2Type;
    }

    public Level getLevel() {
        return level;
    }
}
