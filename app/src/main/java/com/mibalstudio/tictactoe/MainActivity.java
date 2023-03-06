package com.mibalstudio.tictactoe;

import static com.mibalstudio.tictactoe.R.id.level1Radio;
import static com.mibalstudio.tictactoe.R.id.level2Radio;
import static com.mibalstudio.tictactoe.R.id.level3Radio;
import static com.mibalstudio.tictactoe.model.config.Level.LEVEL1;
import static com.mibalstudio.tictactoe.model.config.Level.LEVEL2;
import static com.mibalstudio.tictactoe.model.config.Level.LEVEL3;
import static com.mibalstudio.tictactoe.model.config.PlayerType.COMPUTER;
import static com.mibalstudio.tictactoe.model.config.PlayerType.USER;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.mibalstudio.tictactoe.component.config.CommandLineArguments;
import com.mibalstudio.tictactoe.model.config.Level;
import com.mibalstudio.tictactoe.model.config.PlayerType;


public class MainActivity extends AppCompatActivity {

    public final static String ARGUMENTS_KEY = "args";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(final View view) {
        final Intent intent = new Intent(this,
                GameActivity.class);
        final CommandLineArguments arguments = configureAllArguments();
        intent.putExtra(ARGUMENTS_KEY, arguments);
        startActivity(intent);
    }

    private CommandLineArguments configureAllArguments() {
        PlayerType player2Type;
        final Switch aSwitch = findViewById(R.id.userSwitch);
        if (aSwitch.isChecked()) {
            player2Type = USER;
        } else {
            player2Type = COMPUTER;
        }
        final RadioGroup radios = findViewById(R.id.radioGroup);
        final int radioId = radios.getCheckedRadioButtonId();
        Level level = null;
        if (radioId == level1Radio) {
            level = LEVEL1;
        }
        if (radioId == level2Radio) {
            level = LEVEL2;
        }
        if (radioId == level3Radio) {
            level = LEVEL3;
        }
        return new CommandLineArguments(player2Type, level);
    }
}