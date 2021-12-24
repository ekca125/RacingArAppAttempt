package com.ekcapaper.racingar.activity.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ekcapaper.racingar.kit.R;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
    }
}