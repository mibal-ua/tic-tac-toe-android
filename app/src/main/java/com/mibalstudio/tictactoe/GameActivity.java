package com.mibalstudio.tictactoe;

import static com.mibalstudio.tictactoe.MainActivity.ARGUMENTS_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mibalstudio.tictactoe.component.DataPrinter;
import com.mibalstudio.tictactoe.component.Game;
import com.mibalstudio.tictactoe.component.GameFactory;
import com.mibalstudio.tictactoe.component.GameOverHandler;
import com.mibalstudio.tictactoe.component.UserInputReader;
import com.mibalstudio.tictactoe.component.config.CommandLineArguments;
import com.mibalstudio.tictactoe.model.game.Cell;
import com.mibalstudio.tictactoe.model.game.GameTable;

public class GameActivity extends AppCompatActivity implements DataPrinter, UserInputReader, GameOverHandler {

    private Thread gameThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final Intent intent = getIntent();
        final CommandLineArguments args = (CommandLineArguments) intent.getSerializableExtra(ARGUMENTS_KEY);
        printInfoMessage(args.getLevel() + " " + args.getPlayer2Type()); // TODO delete

        gameThread = new Thread(() -> {
            Looper.prepare();
            GameFactory gameFactory = new GameFactory(args, this);
            Game game = gameFactory.create();

            game.play();
        });
        gameThread.start();
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
        // TODO
    }

    @Override
    public Cell getUserInput() {
        return null;
    }
}