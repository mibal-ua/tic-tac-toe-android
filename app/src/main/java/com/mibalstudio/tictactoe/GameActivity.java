package com.mibalstudio.tictactoe;

import static com.mibalstudio.tictactoe.MainActivity.ARGUMENTS_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mibalstudio.tictactoe.component.CellToNumberConverter;
import com.mibalstudio.tictactoe.component.DataPrinter;
import com.mibalstudio.tictactoe.component.Game;
import com.mibalstudio.tictactoe.component.GameFactory;
import com.mibalstudio.tictactoe.component.GameOverHandler;
import com.mibalstudio.tictactoe.component.UserInputReader;
import com.mibalstudio.tictactoe.component.config.CommandLineArguments;
import com.mibalstudio.tictactoe.model.game.Cell;
import com.mibalstudio.tictactoe.model.game.GameTable;
import com.mibalstudio.tictactoe.model.game.Sign;

public class GameActivity extends AppCompatActivity implements DataPrinter, UserInputReader, GameOverHandler {

    public static final int COlS_N_ROWS = 3;

    private Thread gameThread;

    private char clickedIndex;

    private CommandLineArguments args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final Intent intent = getIntent();
        final CommandLineArguments args = (CommandLineArguments) intent.getSerializableExtra(ARGUMENTS_KEY);
        this.args = args;
        printInfoMessage(args.getLevel() + " " + args.getPlayer2Type()); // TODO delete

        startNewGame();
    }

    private void startNewGame() {
        gameThread = new Thread(() -> {
            Looper.prepare();
            GameFactory gameFactory = new GameFactory(args, this);
            Game game = gameFactory.create();

            game.play();
        });
        gameThread.start();
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

    /*
        cell id contract: cell${numberOfCell}

            1 | 2 | 3
            –––––––––
            4 | 5 | 6
            –––––––––
            7 | 8 | 9

        example: `android:id="@+id/cell4"`
     */
    @Override
    public void printGameTable(final GameTable gameTable) {
        runOnUiThread(() -> {
            for (int i = 0; i < COlS_N_ROWS; i++) {
                for (int j = 0; j < COlS_N_ROWS; j++) {
                    final Cell cell = new Cell(i, j);
                    final Sign sign = gameTable.getSign(cell);

                    char cellId = new CellToNumberConverter().toNumber(cell);
                    final ImageView imageView = findViewById(getResources().getIdentifier(
                            "cell" + cellId, "id",
                            this.getPackageName()));

                    imageView.setImageResource(sign.getResourceId());
                }
            }
        });
    }

    @Override
    public void gameOver() {
        // TODO
    }

    @Override
    public Cell getUserInput() {
        synchronized (GameActivity.this) {
            try {
                wait();
            } catch (final InterruptedException exception) {
                exception.printStackTrace();
                System.exit(2);
            }
        }
        printInfoMessage("cell " + clickedIndex);
        return new CellToNumberConverter().toCell(clickedIndex);
    }

    public void makeMove(final View v) {
        synchronized (GameActivity.this) {
            final String fullName = getResources().getResourceName(v.getId());
            clickedIndex = fullName.charAt(fullName.length() - 1);
            GameActivity.this.notifyAll();
        }
    }

    public void restartGame(final View v) {
        startNewGame();
    }
}