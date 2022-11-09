package com.mibalstudio.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mibalstudio.tictactoe.component.DataPrinter;
import com.mibalstudio.tictactoe.component.Game;
import com.mibalstudio.tictactoe.component.GameOverHandler;
import com.mibalstudio.tictactoe.component.UserInputReader;
import com.mibalstudio.tictactoe.model.game.Cell;
import com.mibalstudio.tictactoe.model.game.GameTable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void start(final String[] args) {
        // onCLick() create arguments and send it to gameFactory
        final GameFactory gameFactory = new GameFactory(args);
        Game game = gameFactory.create();
        game.play();
    }

}