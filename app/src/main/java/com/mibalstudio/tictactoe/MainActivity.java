package com.mibalstudio.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mibalstudio.tictactoe.component.DataPrinter;
import com.mibalstudio.tictactoe.component.GameOverHandler;
import com.mibalstudio.tictactoe.component.UserInputReader;
import com.mibalstudio.tictactoe.model.game.Cell;
import com.mibalstudio.tictactoe.model.game.GameTable;

public class MainActivity extends AppCompatActivity implements DataPrinter, UserInputReader, GameOverHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void printInstructions() {

    }

    @Override
    public void printInfoMessage(String message) {

    }

    @Override
    public void printErrorMessage(String message) {

    }

    @Override
    public void printGameTable(GameTable gameTable) {

    }

    @Override
    public void gameOver() {

    }

    @Override
    public Cell getUserInput() {
        return null;
    }
}