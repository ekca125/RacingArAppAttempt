package com.practice.photonandroidjavascript;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.stream.Collectors;

import app.cash.quickjs.QuickJs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try (QuickJs engine = QuickJs.create()) {
            Log.d("Greeting", engine.evaluate("'hello world'.toUpperCase();").toString());

            AssetManager am = getResources().getAssets();
            try {
                InputStream inputStream =  am.open("a.js");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String source = bufferedReader.lines().collect(Collectors.joining("\n"));
                Log.d("Greeting", engine.evaluate(source).toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}