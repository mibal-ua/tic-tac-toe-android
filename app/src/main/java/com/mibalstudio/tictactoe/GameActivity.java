package com.mibalstudio.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mibalstudio.tictactoe.component.DataPrinter;
import com.mibalstudio.tictactoe.component.GameOverHandler;
import com.mibalstudio.tictactoe.component.UserInputReader;
import com.mibalstudio.tictactoe.model.game.Cell;
import com.mibalstudio.tictactoe.model.game.GameTable;

public class GameActivity extends AppCompatActivity implements DataPrinter, UserInputReader, GameOverHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    public void printInstructions() {

    }

    @Override
    public void printInfoMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void printErrorMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
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