package com.ekcapaper.racingar.activity.play;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ekcapaper.racingar.kit.R;

public class GameSelectionActivity extends AppCompatActivity {
    Button button_start_flag_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        button_start_flag_game = findViewById(R.id.button_start_flag_game);
        button_start_flag_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}