package com.ekcapaper.racingar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.material.components.R;

public class GameSelectionActivity extends AppCompatActivity {
    Button button_start_single_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        button_start_single_game = findViewById(R.id.button_start_single_game);

    }
}