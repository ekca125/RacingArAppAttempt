package com.practice.photonandroidjavascript;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import app.cash.quickjs.QuickJs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try (QuickJs engine = QuickJs.create()) {
            Log.d("Greeting", engine.evaluate("'hello world'.toUpperCase();").toString());
        }

    }
}